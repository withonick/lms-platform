package lms.platform.eduverse.controllers;
import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.FileStorageService;
import lms.platform.eduverse.services.TutorialService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final TutorialService tutorialService;

    private final CourseService courseService;

    private final FileStorageService fileStorageService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/tutorials")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String tutorials(Model model) {
        model.addAttribute("tutorials", tutorialService.getAllTutorials());
        return "admin/tutorials";
    }

    @GetMapping("/courses")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses";
    }
}
