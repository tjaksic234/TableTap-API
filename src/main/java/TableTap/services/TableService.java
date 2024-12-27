package TableTap.services;

import TableTap.models.dto.CreateTableRequest;
import TableTap.models.dto.TableDTO;

public interface TableService {
    TableDTO createTable(CreateTableRequest request);
}
