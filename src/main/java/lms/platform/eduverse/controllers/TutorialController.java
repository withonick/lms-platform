package lms.platform.eduverse.controllers;

import lms.platform.eduverse.services.TutorialService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tutorials")
public class TutorialController {

    @Value("${tutorials.images.path}")
    private String tutorialsImagesPath;

    private final UserService userService;

    private final TutorialService tutorialService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and @userService.isUserPremium(authentication) or hasRole('ROLE_ADMIN')")
    public String tutorials(Model model) {
        model.addAttribute("tutorials", tutorialService.getAllTutorials());
        return "tutorials/index";
    }

}
