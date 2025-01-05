package TableTap.models.dto;

import TableTap.models.dao.Point;
import TableTap.models.enums.CuisineType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDTO {
    private String name;
    private String description;
    private CuisineType[] cuisineType;
    private Point location;
    private LocalDateTime createdAt;
}
