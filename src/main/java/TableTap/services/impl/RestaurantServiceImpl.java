package TableTap.services.impl;

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

    @Override
    public RestaurantDTO createRestaurant(CreateRestaurantRequest request) {

        return null;
    }
}
