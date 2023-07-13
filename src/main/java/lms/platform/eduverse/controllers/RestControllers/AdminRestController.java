package lms.platform.eduverse.controllers.RestControllers;

import lms.platform.eduverse.dto.CourseDTO;
import lms.platform.eduverse.dto.TutorialDTO;
import lms.platform.eduverse.dto.UserDTO;
import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.TutorialService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    private final TutorialService tutorialService;

    private final CourseService courseService;

    @PostMapping("/users/toggle/ban")
    public ResponseEntity<?> banUser(@RequestParam(name = "user_id") Long userId,
                                           @RequestParam(name = "ban") Boolean ban) {
        try {
            userService.toggleBan(userId, ban);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersDTO());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCoursesDTO());
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialDTO>> getAllDTOTutorials() {
        return ResponseEntity.ok(tutorialService.getAllTutorialsDTO());
    }

}
