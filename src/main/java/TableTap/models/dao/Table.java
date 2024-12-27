package TableTap.models.dao;

import TableTap.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    @Id
    private String id;

    private String restaurantID;

    private int minGuests;

    private int maxGuests;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
