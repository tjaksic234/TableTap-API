package TableTap.services;

import TableTap.models.dao.Reservation;
import TableTap.models.dto.CreateReservationRequest;
import TableTap.models.dto.ReservationDTO;

public interface ReservationService {
    ReservationDTO createReservation(CreateReservationRequest request);
    void sendVerificationEmail(Reservation reservation);
}
