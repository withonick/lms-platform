package lms.platform.eduverse.repositories;

import jakarta.transaction.Transactional;
import lms.platform.eduverse.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByOrderByRatingDesc();

    List<Course> findAllByIsPremium(Boolean isPremium);
}
