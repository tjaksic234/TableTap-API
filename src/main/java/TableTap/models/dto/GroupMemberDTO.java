package TableTap.models.dto;

import TableTap.models.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupMemberDTO {
    private String userId;
    private String groupId;
    private MemberStatus memberStatus;
    private LocalDateTime joinedAt;
}
