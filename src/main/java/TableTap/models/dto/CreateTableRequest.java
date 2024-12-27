package TableTap.models.dto;

import TableTap.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTableRequest {
    private String restaurantID;
    private int minGuests;
    private int maxGuests;
}
