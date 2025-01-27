package TableTap.models.dto;

import TableTap.models.enums.GroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGroupRequest {
    private String name;
    private String description;
    private GroupType groupType;
}
