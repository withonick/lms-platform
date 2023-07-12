package lms.platform.eduverse.controllers;

import lms.platform.eduverse.models.Course;
import lms.platform.eduverse.models.Lesson;
import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.FileStorageService;
import lms.platform.eduverse.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    @Value("${lessons.images.path}")
    private String lessonsImagesPath;

    private final LessonService lessonService;

    private final CourseService courseService;

    private final FileStorageService fileStorageService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/store")
    public String addLesson(@RequestParam(name = "name") String name,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "video") String image,
                              @RequestParam(name = "course_id") Long courseId) {
        try{
            Course course = courseService.getCourseById(courseId);

            if(course == null){
                return "redirect:/course" + courseId + "/materials/create?error=course_not_found";
            }

            Lesson lesson = new Lesson();

            lesson.setName(name);
            lesson.setDescription(description);
            lesson.setCourse(course);
            lesson.setVideo(image);

            lessonService.saveLesson(lesson);

            course.getLessons().add(lesson);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/courses";
    }

    @GetMapping("{id}")
    public String getLesson(@PathVariable Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        Course course = courseService.getCourseById(lesson.getCourse().getId());
        List<Lesson> lessons = lessonService.getLessonsByCourseId(course.getId());
        model.addAttribute("lessons", lessons);
        model.addAttribute("lesson", lesson);
        return "lessons/single-lesson";
    }
}
