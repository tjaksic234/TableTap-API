package TableTap.api;

import TableTap.models.dto.CreateReservationRequest;
import TableTap.models.dto.ReservationDTO;
import TableTap.services.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/reservations")
@Slf4j
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("create")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationRequest request) {
        log.info("Attempting to create a reservation");
        return ResponseEntity.ok(reservationService.createReservation(request));
    }
}
