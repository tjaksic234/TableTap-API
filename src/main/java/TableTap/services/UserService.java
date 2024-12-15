package TableTap.services;

import TableTap.models.dao.User;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    UserDTO getUserById(String id);
}
