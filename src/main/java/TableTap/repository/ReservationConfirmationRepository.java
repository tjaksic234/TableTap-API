package TableTap.repository;

import TableTap.models.dao.ReservationConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationConfirmationRepository extends MongoRepository<ReservationConfirmation, String> {

}
