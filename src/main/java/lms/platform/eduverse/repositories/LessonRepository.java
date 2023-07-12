package lms.platform.eduverse.repositories;

import jakarta.transaction.Transactional;
import lms.platform.eduverse.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseId(Long CourseId);
}
