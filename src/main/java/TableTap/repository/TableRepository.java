package TableTap.repository;

import TableTap.models.dao.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends MongoRepository<Table, String> {
    List<Table> findByRestaurantID(String restaurantID);
}
