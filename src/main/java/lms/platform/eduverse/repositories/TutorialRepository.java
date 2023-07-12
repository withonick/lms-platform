package lms.platform.eduverse.repositories;

import jakarta.transaction.Transactional;
import lms.platform.eduverse.models.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
