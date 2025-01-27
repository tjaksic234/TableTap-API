package TableTap.models.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    private String id;

    @NotBlank
    private String fromUser;

    @NotBlank
    private String toUser;

    @NotBlank
    private String content;

    private String groupId;

    private boolean read;

    private List<String> readBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
