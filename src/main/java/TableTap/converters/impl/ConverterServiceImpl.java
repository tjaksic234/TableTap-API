package TableTap.converters.impl;

import TableTap.converters.ConverterService;
import TableTap.models.dao.Reservation;
import TableTap.models.dao.Restaurant;
import TableTap.models.dao.Table;
import TableTap.models.dao.User;
import TableTap.models.dto.ReservationDTO;
import TableTap.models.dto.RestaurantDTO;
import TableTap.models.dto.TableDTO;
import TableTap.models.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConverterServiceImpl implements ConverterService {
    @Override
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

    @Override
    public RestaurantDTO convertRestaurantToRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setLocation(restaurant.getLocation());
        restaurantDTO.setCuisineType(restaurant.getCuisineType());
        restaurantDTO.setCreatedAt(restaurant.getCreatedAt());
        return restaurantDTO;
    }

    @Override
    public TableDTO convertTableToTableDTO(Table table) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(table.getId());
        tableDTO.setRestaurantID(table.getRestaurantID());
        tableDTO.setMinGuests(table.getMinGuests());
        tableDTO.setMaxGuests(table.getMaxGuests());
        tableDTO.setCreatedAt(table.getCreatedAt());
        return tableDTO;
    }

    @Override
    public List<TableDTO> convertTablesToTableDTOs(List<Table> tableList) {
        List<TableDTO> tableDTOList = new ArrayList<>();

        for (Table table : tableList) {
            TableDTO tableDTO = new TableDTO();
            tableDTO.setId(table.getId());
            tableDTO.setRestaurantID(table.getRestaurantID());
            tableDTO.setMinGuests(table.getMinGuests());
            tableDTO.setMaxGuests(table.getMaxGuests());
            tableDTO.setCreatedAt(table.getCreatedAt());

            tableDTOList.add(tableDTO);
        }
        return tableDTOList;
    }

    @Override
    public ReservationDTO convertReservationToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setEmail(reservation.getEmail());
        reservationDTO.setRestaurantID(reservation.getRestaurantID());
        reservationDTO.setTableID(reservation.getTableID());
        reservationDTO.setNumOfGuests(reservation.getNumOfGuests());
        reservationDTO.setReservationDate(reservation.getReservationDate());
        reservationDTO.setValidUntil(reservation.getValidUntil());
        return reservationDTO;
    }

    @Override
    public List<RestaurantDTO> convertRestaurantsToRestaurantDTOs(List<Restaurant> restaurantList) {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();

        for (Restaurant restaurant : restaurantList) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setName(restaurant.getName());
            restaurantDTO.setDescription(restaurant.getDescription());
            restaurantDTO.setCuisineType(restaurant.getCuisineType());
            restaurantDTO.setLocation(restaurant.getLocation());
            restaurantDTO.setCreatedAt(restaurant.getCreatedAt());

            restaurantDTOList.add(restaurantDTO);
        }
        return restaurantDTOList;
    }
}
