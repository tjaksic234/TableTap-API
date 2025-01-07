package TableTap.services;

import TableTap.models.dto.CreateTableRequest;
import TableTap.models.dto.TableDTO;

import java.util.List;

public interface TableService {
    TableDTO createTable(CreateTableRequest request);
    List<TableDTO> getTablesForRestaurant(String restaurantId);
}
