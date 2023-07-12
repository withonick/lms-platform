package lms.platform.eduverse.services;

import lms.platform.eduverse.dto.CourseDTO;
import lms.platform.eduverse.mapper.CourseMapper;
import lms.platform.eduverse.models.Course;
import lms.platform.eduverse.models.User;
import lms.platform.eduverse.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public boolean isCourseCreator(Long id, User user) {
        Course course = courseRepository.findById(id).orElse(null);
        return course != null && course.getUser().equals(user) && course.getIsPremium();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllPremiumCourses() {
        return courseRepository.findAllByIsPremium(true);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

}