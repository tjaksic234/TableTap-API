package TableTap.services;

import TableTap.models.dto.CreateRestaurantRequest;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.enums.CuisineType;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO createRestaurant(CreateRestaurantRequest request);
    RestaurantDTO getRestaurantById(String restaurantId);
    List<RestaurantDTO> getAllRestaurants();
    List<RestaurantDTO> filterRestaurants(CuisineType[] type, String sort, String search);
}
