package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.NotFoundException;
import TableTap.models.dao.User;
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

    private final ConverterService converterService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return converterService.convertUserToUserDTO(user);
    }
}
