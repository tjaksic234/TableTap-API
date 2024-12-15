package TableTap.converters;

import TableTap.models.dao.User;
import TableTap.models.dto.UserDTO;

public interface ConverterService {
    UserDTO convertUserToUserDTO(User user);
}
