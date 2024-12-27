package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.EntityAlreadyExistsException;
import TableTap.models.dao.Point;
import TableTap.models.dao.Restaurant;
import TableTap.models.dto.CreateRestaurantRequest;
import TableTap.models.dto.RestaurantDTO;
import TableTap.repository.RestaurantRepository;
import TableTap.services.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final ConverterService converterService;

    @Override
    public RestaurantDTO createRestaurant(CreateRestaurantRequest request) {
        String normalizedName = request.getName().toUpperCase().trim();

        if (restaurantRepository.findByName(normalizedName).isPresent()) {
            throw new EntityAlreadyExistsException("Restaurant with that name is already registered");
        }

        Restaurant restaurant = new Restaurant();

        Point point = new Point(request.getLongitude(), request.getLatitude());

        restaurant.setName(request.getName().toUpperCase().trim());
        restaurant.setDescription(request.getDescription().trim());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setLocation(point);

        restaurantRepository.save(restaurant);

        log.info("Restaurant successfully created");
        return converterService.convertRestaurantToRestaurantDTO(restaurant);
    }
}
