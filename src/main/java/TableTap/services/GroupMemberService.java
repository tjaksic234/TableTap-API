package TableTap.services;

import TableTap.models.dto.CreateGroupMemberRequest;
import TableTap.models.dto.GroupMemberDTO;

public interface GroupMemberService {
    GroupMemberDTO join(CreateGroupMemberRequest request);
}
