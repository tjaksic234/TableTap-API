package TableTap.models.dao;

import TableTap.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String id;

    private String email;

    private String restaurantID;

    private String tableID;

    private ReservationStatus status = ReservationStatus.ACTIVE;

    private int numOfGuests;

    private LocalDateTime reservationDate;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime validUntil;
}
