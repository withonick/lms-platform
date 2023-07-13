package lms.platform.eduverse.controllers.RestControllers;

import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.TutorialService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    private final TutorialService tutorialService;

    private final CourseService courseService;

}
