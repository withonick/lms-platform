package lms.platform.eduverse.controllers;

import lms.platform.eduverse.models.Course;
import lms.platform.eduverse.models.Lesson;
import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.FileStorageService;
import lms.platform.eduverse.services.LessonService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.List;

@RequestMapping("/courses")
@Controller
@RequiredArgsConstructor
public class CourseController {

    @Value("${courses.images.path}")
    private String coursesImagesPath;

    private final CourseService courseService;

    private final FileStorageService fileStorageService;

    private final UserService userService;

    private final LessonService lessonService;

    @GetMapping
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/index";
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PostMapping("/store")
    public String addCourse(@RequestParam(name = "name") String name,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "image") MultipartFile image,
                            @RequestParam(name = "isPremium", defaultValue = "false") boolean isPremium){
        try {
            Course course = new Course();
            course.setName(name);
            course.setDescription(description);

            if (!image.isEmpty()) {
                String fileName = fileStorageService.saveFile(image, coursesImagesPath);
                course.setImage(fileName);
            }

            course.setIsPremium(isPremium);
            Course newCourse = courseService.saveCourse(course);

            if (newCourse != null) {
                return "redirect:/courses?success";
            } else {
                return "redirect:/courses?error";
            }

        } catch (IOException e) {
            return "redirect:/error?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String course(@PathVariable Long id, Model model) throws NoHandlerFoundException {
        if (courseService.getCourseById(id) != null) {
            Course course = courseService.getCourseById(id);
            if (course.getIsPremium()){
                if (userService.getCurrentSessionUser().getIsPremium() || userService.isAdmin(userService.getCurrentSessionUser())) {
                    model.addAttribute("course", course);
                    model.addAttribute("lessons", lessonService.getLessonsByCourseId(id));
                    return "courses/single-course";
                } else {
                    return "redirect:/courses?premium-course";
                }
            } else {
                model.addAttribute("lessons", lessonService.getLessonsByCourseId(id));
                model.addAttribute("course", course);
                return "courses/single-course";
            }
        } else {
            throw new NoHandlerFoundException("GET", "/courses/" + id, null);
        }
    }


    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update")
    public String updateCourse(@RequestParam(name = "course_id") Long id,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "image") MultipartFile image,
                               @RequestParam(name = "isPremium", defaultValue = "false") boolean isPremium) {
        try {
            Course course = courseService.getCourseById(id);
            course.setName(name);
            course.setDescription(description);
            course.setIsPremium(isPremium);

            if (!image.isEmpty()) {
                String fileName = fileStorageService.saveFile(image, coursesImagesPath);
                course.setImage(fileName);
            }

            Course updatedCourse = courseService.saveCourse(course);

            if (updatedCourse != null) {
                return "redirect:/courses?success";
            } else {
                return "redirect:/courses?error";
            }

        } catch (IOException e) {
            return "redirect:/error?error";
        }
    }

}
