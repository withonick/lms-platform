package lms.platform.eduverse.repositories;

import jakarta.transaction.Transactional;
import lms.platform.eduverse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


}
