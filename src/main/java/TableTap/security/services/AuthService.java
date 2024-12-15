package TableTap.security.services;

import TableTap.models.dto.LoginRequest;
import TableTap.models.dto.LoginResponse;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;

public interface AuthService {
    UserDTO register(RegisterUserRequest request);
    LoginResponse login(LoginRequest request);
}
