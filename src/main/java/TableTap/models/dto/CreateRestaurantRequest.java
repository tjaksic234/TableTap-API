package TableTap.models.dto;

import TableTap.models.enums.CuisineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {
    private String name;
    private String description;
    private CuisineType[] cuisineType;
    private String longitude;
    private String latitude;
}
