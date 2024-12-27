package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.BadRequestException;
import TableTap.exceptions.NotFoundException;
import TableTap.models.dao.Reservation;
import TableTap.models.dao.ReservationConfirmation;
import TableTap.models.dao.Restaurant;
import TableTap.models.dao.Table;
import TableTap.models.dto.CreateReservationRequest;
import TableTap.models.dto.ReservationDTO;
import TableTap.models.enums.ReservationStatus;
import TableTap.repository.*;
import TableTap.security.utils.EmailTemplates;
import TableTap.security.utils.Helper;
import TableTap.services.ReservationService;
import TableTap.services.SendGridEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final ReservationConfirmationRepository reservationConfirmationRepository;
    private final ConverterService converterService;
    private final SendGridEmailService sendGridEmailService;

    @Qualifier("emailFromAddress")
    private String EMAIL_FROM;

    @Value("${spring.backend.url}")
    private String BACKEND_URL;

    @Override
    public ReservationDTO createReservation(CreateReservationRequest request) {

        userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User email not found"));

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

        sendVerificationEmail(reservation);

        log.info("Reservation of tableID: {} at the date: {} was successful!",
                reservation.getTableID(), reservation.getReservationDate());
        return converterService.convertReservationToReservationDTO(reservation);
    }

    @Override
    public void sendVerificationEmail(Reservation reservation) {
        String reservationCode = Helper.generateRandomString();
        Optional<Restaurant> restaurant = restaurantRepository.findById(reservation.getRestaurantID());
        ReservationConfirmation reservationConfirmation = new ReservationConfirmation();
        reservationConfirmation.setReservationCode(reservationCode);
        reservationConfirmation.setReservationID(reservation.getId());
        reservationConfirmation.setEmail(reservation.getEmail());

        reservationConfirmationRepository.save(reservationConfirmation);

        String reservationConfirmationURL = BACKEND_URL + "/api/auth/verify?invitationId=" + reservationConfirmation.getId()
                + "&reservationCode=" + reservationCode;

        sendGridEmailService.sendHtml(EMAIL_FROM, reservation.getEmail(), "Reservation Confirmation",
                EmailTemplates.getReservationConfirmation(reservation.getEmail(),
                        restaurant.get().getName(), reservation.getReservationDate(), reservation.getValidUntil(), reservation.getNumOfGuests()));
    }
}
