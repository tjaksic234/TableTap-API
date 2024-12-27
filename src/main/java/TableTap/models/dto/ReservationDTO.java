package TableTap.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String email;
    private String restaurantID;
    private String tableID;
    private int numOfGuests;
    private LocalDateTime reservationDate;
    private LocalDateTime validUntil;
}
