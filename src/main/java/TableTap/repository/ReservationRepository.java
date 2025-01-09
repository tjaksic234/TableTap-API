package TableTap.repository;

import TableTap.models.dao.Reservation;
import TableTap.models.enums.ReservationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByTableIDAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            String tableId, ReservationStatus status,
            LocalDateTime endTime, LocalDateTime startTime
    );
}
