package TableTap.converters;

import TableTap.models.dao.Reservation;
import TableTap.models.dao.Restaurant;
import TableTap.models.dao.Table;
import TableTap.models.dao.User;
import TableTap.models.dto.ReservationDTO;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.dto.TableDTO;
import TableTap.models.dto.UserDTO;

import java.util.List;

public interface ConverterService {
    UserDTO convertUserToUserDTO(User user);
    RestaurantDTO convertRestaurantToRestaurantDTO(Restaurant restaurant);
    TableDTO convertTableToTableDTO(Table table);
    List<TableDTO> convertTablesToTableDTOs(List<Table> tableList);
    ReservationDTO convertReservationToReservationDTO(Reservation reservation);
    List<RestaurantDTO> convertRestaurantsToRestaurantDTOs(List<Restaurant> restaurantList);
}
