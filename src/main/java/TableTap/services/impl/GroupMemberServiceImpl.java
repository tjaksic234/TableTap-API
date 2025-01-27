package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.EntityAlreadyExistsException;
import TableTap.exceptions.IllegalArgumentException;
import TableTap.models.dao.GroupMember;
import TableTap.models.dto.CreateGroupMemberRequest;
import TableTap.models.dto.GroupMemberDTO;
import TableTap.models.enums.MemberStatus;
import TableTap.repository.GroupMemberRepository;
import TableTap.services.GroupMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;

    private final ConverterService converterService;

    @Override
    public GroupMemberDTO join(CreateGroupMemberRequest request) {

        if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("User id is null or empty");
        }

        if (request.getGroupId() == null || request.getGroupId().trim().isEmpty()) {
            throw new IllegalArgumentException("Group id is null or empty");
        }

        if (groupMemberRepository.existsByUserIdAndGroupIdAndMemberStatusIs(request.getUserId(), request.getGroupId(), MemberStatus.ACTIVE)) {
            throw new EntityAlreadyExistsException("Group member already exists");
        }

        GroupMember groupMember = new GroupMember();
        groupMember.setUserId(request.getUserId());
        groupMember.setGroupId(request.getGroupId());
        groupMember.setMemberStatus(MemberStatus.ACTIVE);
        groupMemberRepository.save(groupMember);

        log.info("Group member joined successfully - {}", groupMember.getId());
        return converterService.convertGroupMemberToGroupMemberDTO(groupMember);
    }
}
