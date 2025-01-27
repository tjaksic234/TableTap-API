package TableTap.api;

import TableTap.models.dto.CreateGroupRequest;
import TableTap.models.dto.GroupDTO;
import TableTap.services.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/groups")
@Slf4j
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("create")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody CreateGroupRequest request) {
        log.info("Attempting to create a group");
        return ResponseEntity.ok(groupService.createGroup(request));
    }
}
