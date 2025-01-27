package TableTap.api;

import TableTap.models.dto.CreateGroupMemberRequest;
import TableTap.models.dto.GroupMemberDTO;
import TableTap.services.GroupMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/groupMembers")
@Slf4j
@AllArgsConstructor
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    @PostMapping("join")
    public ResponseEntity<GroupMemberDTO> createGroupMember(@RequestBody CreateGroupMemberRequest request) {
        log.info("Attempting to join a group");
        return ResponseEntity.ok(groupMemberService.join(request));
    }
}
