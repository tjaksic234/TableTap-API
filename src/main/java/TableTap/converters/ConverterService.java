package TableTap.converters;

import TableTap.models.dao.*;
import TableTap.models.dto.*;

import java.util.List;

public interface ConverterService {
    UserDTO convertUserToUserDTO(User user);
    RestaurantDTO convertRestaurantToRestaurantDTO(Restaurant restaurant);
    TableDTO convertTableToTableDTO(Table table);
    GroupDTO convertGroupToGroupDTO(Group group);
    MessageDTO convertMessageToMessageDTO(Message message);
    GroupMemberDTO convertGroupMemberToGroupMemberDTO(GroupMember groupMember);
    List<TableDTO> convertTablesToTableDTOs(List<Table> tableList);
    ReservationDTO convertReservationToReservationDTO(Reservation reservation);
    List<RestaurantDTO> convertRestaurantsToRestaurantDTOs(List<Restaurant> restaurantList);
}
