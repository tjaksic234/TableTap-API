package TableTap.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {
    private String id;
    private String restaurantID;
    private int minGuests;
    private int maxGuests;
    private LocalDateTime createdAt;
}
