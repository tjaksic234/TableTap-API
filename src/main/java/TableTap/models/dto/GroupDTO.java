package TableTap.models.dto;

import TableTap.models.enums.GroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDTO {
    private String name;
    private String description;
    private GroupType groupType;
    private LocalDateTime createdAt;
}
