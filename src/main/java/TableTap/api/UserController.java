package TableTap.api;

import TableTap.exceptions.UserAlreadyExistsException;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;
import TableTap.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            log.info("Attempting to register a user");
            return ResponseEntity.ok(userService.registerUser(request));
        } catch (UserAlreadyExistsException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
