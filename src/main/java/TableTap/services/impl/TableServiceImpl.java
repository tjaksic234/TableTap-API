package TableTap.services.impl;

import TableTap.converters.ConverterService;
import TableTap.exceptions.BadRequestException;
import TableTap.exceptions.NotFoundException;
import TableTap.models.dao.Table;
import TableTap.models.dto.CreateTableRequest;
import TableTap.models.dto.TableDTO;
import TableTap.repository.RestaurantRepository;
import TableTap.repository.TableRepository;
import TableTap.services.TableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    private final RestaurantRepository restaurantRepository;

    private final ConverterService converterService;

    @Override
    public TableDTO createTable(CreateTableRequest request) {

        if (restaurantRepository.findById(request.getRestaurantID()).isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        if (request.getMinGuests() < 1 || request.getMaxGuests() <= request.getMinGuests()) {
            throw new BadRequestException("Unacceptable guest values entered");
        }

        Table table = new Table();
        table.setRestaurantID(request.getRestaurantID());
        table.setMinGuests(request.getMinGuests());
        table.setMaxGuests(request.getMaxGuests());

        tableRepository.save(table);

        log.info("Table successfully created!");
        return converterService.convertTableToTableDTO(table);
    }

    @Override
    public List<TableDTO> getTablesForRestaurant(String restaurantId) {
        if (restaurantRepository.findById(restaurantId).isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        List<Table> tableList = tableRepository.findByRestaurantID(restaurantId);

        log.info("#{} tables fetched for restaurant({})", tableList.size(), restaurantId);
        return converterService.convertTablesToTableDTOs(tableList);
    }
}
