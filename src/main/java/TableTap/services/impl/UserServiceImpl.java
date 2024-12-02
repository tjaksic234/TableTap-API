package TableTap.services.impl;

import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;
import TableTap.repository.UserRepository;
import TableTap.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(RegisterUserRequest request) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(String id) {
        return null;
    }
}
