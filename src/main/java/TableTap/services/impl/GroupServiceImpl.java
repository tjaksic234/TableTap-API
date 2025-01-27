package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.models.dao.Group;
import TableTap.models.dto.CreateGroupRequest;
import TableTap.models.dto.GroupDTO;
import TableTap.repository.GroupRepository;
import TableTap.services.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final ConverterService converterService;

    @Override
    public GroupDTO createGroup(CreateGroupRequest createGroupRequest) {
        Group group = new Group();
        group.setName(createGroupRequest.getName());
        group.setDescription(createGroupRequest.getDescription());
        if (createGroupRequest.getGroupType() != null) {
            group.setGroupType(createGroupRequest.getGroupType());
        }
        groupRepository.save(group);

        log.info("Successfully created group {}", group.getName());
        return converterService.convertGroupToGroupDTO(group);
    }
}
