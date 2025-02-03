package TableTap.models.dto;

import TableTap.models.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private String fromUser;
    private String toUser;
    private String content;
    private MessageType messageType;
    private String groupId;
    private LocalDateTime createdAt;
}
