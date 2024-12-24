package TableTap.services;

import TableTap.models.dto.CreateRestaurantRequest;
import TableTap.models.dto.RestaurantDTO;

public interface RestaurantService {
    RestaurantDTO createRestaurant(CreateRestaurantRequest request);
}
