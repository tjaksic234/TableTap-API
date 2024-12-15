package TableTap.security.api;

import TableTap.exceptions.InvalidPhoneException;
import TableTap.exceptions.UserAlreadyExistsException;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;
import TableTap.security.services.AuthService;
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
@AllArgsConstructor
@Slf4j
@RequestMapping(API_ROUTE + "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            log.info("Attempting to register a user");
            return ResponseEntity.ok(authService.register(request));
        } catch (UserAlreadyExistsException | InvalidPhoneException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
