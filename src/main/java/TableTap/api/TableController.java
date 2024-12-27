package TableTap.api;

import TableTap.models.dto.CreateTableRequest;
import TableTap.models.dto.TableDTO;
import TableTap.services.TableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static TableTap.security.utils.Constants.API_ROUTE;

@RestController
@RequestMapping(API_ROUTE + "/tables")
@Slf4j
@AllArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping("create")
    public ResponseEntity<TableDTO> createTable(@RequestBody CreateTableRequest request) {
        log.info("Creating new table for restaurant with ID: {}", request.getRestaurantID());
        return ResponseEntity.ok(tableService.createTable(request));
    }
}
