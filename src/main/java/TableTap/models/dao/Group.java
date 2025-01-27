package TableTap.models.dao;

import TableTap.models.enums.GroupType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    @Id
    private String id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 100)
    private String description;

    @NotBlank
    private GroupType groupType = GroupType.PUBLIC;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
