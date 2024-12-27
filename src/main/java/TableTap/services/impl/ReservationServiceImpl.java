package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.BadRequestException;
import TableTap.exceptions.NotFoundException;
import TableTap.models.dao.Reservation;
import TableTap.models.dao.Table;
import TableTap.models.dto.CreateReservationRequest;
import TableTap.models.dto.ReservationDTO;
import TableTap.models.enums.ReservationStatus;
import TableTap.repository.ReservationRepository;
import TableTap.repository.RestaurantRepository;
import TableTap.repository.TableRepository;
import TableTap.services.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final RestaurantRepository restaurantRepository;

    private final TableRepository tableRepository;

    private final MongoTemplate mongoTemplate;

    private final ConverterService converterService;

    @Override
    public ReservationDTO createReservation(CreateReservationRequest request) {

        Table table = tableRepository.findById(request.getTableID()).orElseThrow(() -> new NotFoundException("Invalid tableID"));

        if (restaurantRepository.findById(request.getRestaurantID()).isEmpty()) {
            throw new NotFoundException("Invalid RestaurantID");
        }

        if (request.getNumOfGuests() < 1) {
            throw new BadRequestException("Unacceptable number of guests entered");
        }

        if (table.getMaxGuests() < request.getNumOfGuests()) {
            throw new BadRequestException("Unsupported number of guests for the selected table");
        }

        LocalDateTime requestedTime = request.getReservationDate();
        log.warn("reservationDate: {}", requestedTime);
        LocalDateTime oneHourBefore = requestedTime.minusHours(1);
        LocalDateTime oneHourAfter = requestedTime.plusHours(1);

        List<Reservation> recentReservations = reservationRepository.findByEmailAndStatusAndReservationDateBetween(
                request.getEmail(), ReservationStatus.ACTIVE, oneHourBefore, oneHourAfter);

        if (!recentReservations.isEmpty()) {
            throw new BadRequestException("Must wait at least 1 hour between reservations!");
        }


        Reservation reservation = new Reservation();
        reservation.setEmail(request.getEmail());
        reservation.setRestaurantID(request.getRestaurantID());
        reservation.setTableID(request.getTableID());
        reservation.setNumOfGuests(request.getNumOfGuests());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setValidUntil(request.getReservationDate().plusHours(1));

        reservationRepository.save(reservation);

        log.info("Reservation of tableID: {} at the date: {} was successful!",
                reservation.getTableID(), reservation.getReservationDate());
        return converterService.convertReservationToReservationDTO(reservation);
    }
}
