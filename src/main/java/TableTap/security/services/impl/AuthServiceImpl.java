package TableTap.security.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.InvalidPhoneException;
import TableTap.exceptions.UserAlreadyExistsException;
import TableTap.models.dao.User;
import TableTap.models.dto.LoginRequest;
import TableTap.models.dto.LoginResponse;
import TableTap.models.dto.RegisterUserRequest;
import TableTap.models.dto.UserDTO;
import TableTap.repository.UserRepository;
import TableTap.security.services.AuthService;
import TableTap.security.utils.JwtUtils;
import TableTap.security.utils.PhoneHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConverterService converterService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    public UserDTO register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail()) || request.getEmail() == null) {
            throw new UserAlreadyExistsException("Email is already taken or it is not in the correct format");
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        if (request.getPhone() != null && PhoneHelper.validatePhoneNumber(request.getPhone())) {
            user.setPhone(request.getPhone());
        } else  {
            throw new InvalidPhoneException("Invalid phone number entered");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        log.info("Registered a user with id: {} successfully", user.getId());
        return converterService.convertUserToUserDTO(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        LoginResponse response = new LoginResponse();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateJwtToken(authentication);

        response.setAccessToken(accessToken);

        log.info("Successful login with: {}", request.getEmail());
        return response;
    }
}
