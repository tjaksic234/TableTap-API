package TableTap.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reservationConfirmations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationConfirmation {
    @Id
    private String id;

    private String email;

    private String reservationID;

    private String reservationCode;

    @CreatedDate
    private LocalDateTime createdAt;
}
