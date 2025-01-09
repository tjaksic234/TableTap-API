package TableTap.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequest {
    private String email;
    private String restaurantID;
    private String tableID;
    private int numOfGuests;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
