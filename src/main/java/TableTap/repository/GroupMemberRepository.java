package TableTap.repository;

import TableTap.models.dao.GroupMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends MongoRepository<GroupMember, String> {
}
