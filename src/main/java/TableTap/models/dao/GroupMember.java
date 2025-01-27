package TableTap.models.dao;

import TableTap.models.enums.MemberStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "groupMembers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupMember {
    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String groupId;

    @NotBlank
    private MemberStatus memberStatus;

    @CreatedDate
    private LocalDateTime joinedAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
