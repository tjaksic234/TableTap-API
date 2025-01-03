package TableTap.converters;

import TableTap.models.dao.Reservation;
import TableTap.models.dao.Restaurant;
import TableTap.models.dao.Table;
import TableTap.models.dao.User;
import TableTap.models.dto.ReservationDTO;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.dto.TableDTO;
import TableTap.models.dto.UserDTO;

public interface ConverterService {
    UserDTO convertUserToUserDTO(User user);
    RestaurantDTO convertRestaurantToRestaurantDTO(Restaurant restaurant);
    TableDTO convertTableToTableDTO(Table table);
    ReservationDTO convertReservationToReservationDTO(Reservation reservation);
}
