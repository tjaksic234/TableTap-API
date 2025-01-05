package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.EntityAlreadyExistsException;
import TableTap.models.dao.Point;
import TableTap.models.dao.Restaurant;
import TableTap.models.dto.CreateRestaurantRequest;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.enums.CuisineType;
import TableTap.repository.RestaurantRepository;
import TableTap.services.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final ConverterService converterService;

    private final MongoTemplate mongoTemplate;

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

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return converterService.convertRestaurantsToRestaurantDTOs(restaurantRepository.findAll());
    }

    @Override
    public List<RestaurantDTO> filterRestaurants(CuisineType[] type, String sort, String search) {
        List<AggregationOperation> operations = new ArrayList<>();
        Criteria criteria = new Criteria();

        if (type != null && type.length >= 1) {
            criteria.and("cuisineType").in((Object[]) type);
        }


        if (search != null && !search.trim().isEmpty()) {
            TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(search);
            operations.add(Aggregation.match(textCriteria));
        }

        MatchOperation matchOperation = Aggregation.match(criteria);
        operations.add(matchOperation);

        ProjectionOperation projectionOperation = Aggregation.project()
                .andInclude("name", "description", "cuisineType", "location", "createdAt");
        operations.add(projectionOperation);

        if (sort != null && !sort.trim().isEmpty()) {
            Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            SortOperation sortOperation = Aggregation.sort(Sort.by(direction, "name"));
            operations.add(sortOperation);
        } else {
            SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "name"));
            operations.add(sortOperation);
            log.info("No sort order selected. Using default ascending order by name");
        }

        Aggregation aggregation = Aggregation.newAggregation(operations);

        AggregationResults<Restaurant> results = mongoTemplate.aggregate(aggregation, "restaurants", Restaurant.class);

        return converterService.convertRestaurantsToRestaurantDTOs(results.getMappedResults());
    }
}
