package TableTap.services;

import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerUser(RegisterUserRequest request);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
}
