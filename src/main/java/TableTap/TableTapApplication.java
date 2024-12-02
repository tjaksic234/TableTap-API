package TableTap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "TableTap.repository")
public class TableTapApplication {
    public static void main(String[] args) {
        SpringApplication.run(TableTapApplication.class, args);
    }
}
