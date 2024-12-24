package TableTap.repository;

import TableTap.models.dao.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
        Optional<Restaurant> findByName(String name);
}
