package TableTap.services;

import TableTap.models.dto.CreateGroupRequest;
import TableTap.models.dto.GroupDTO;

public interface GroupService {
    GroupDTO createGroup(CreateGroupRequest createGroupRequest);
}
