package TableTap.api;

import TableTap.models.dto.CreateRestaurantRequest;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.enums.CuisineType;
import TableTap.services.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/restaurants")
@Slf4j
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("create")
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        log.info("Creating a new restaurant");
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }

    @GetMapping("all")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        log.info("Fetching all restaurants");
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("filter")
    public ResponseEntity<List<RestaurantDTO>> filterRestaurants(
            @RequestParam(required = false) CuisineType[] type,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String search
    ) {
        log.info("Fetching restaurants by filters: ({}), ({}), ({}),", type, sort, search);
        return ResponseEntity.ok(restaurantService.filterRestaurants(type, sort, search));
    }
}
