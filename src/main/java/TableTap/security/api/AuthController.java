package TableTap.security.api;

import TableTap.models.dto.LoginRequest;
import TableTap.models.dto.LoginResponse;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;
import TableTap.security.services.AuthService;
import TableTap.security.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
  import org.springframework.http.ResponseCookie;
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

    private final JwtUtils jwtUtils;

    @PostMapping("register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserRequest request) {
            log.info("Attempting to register a user");
            return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        log.info("Attempting to login");
        LoginResponse response = authService.login(request);
        ResponseCookie cookie = jwtUtils.createJwtCookie(response.getAccessToken());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
    }
}
