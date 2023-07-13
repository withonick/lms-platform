package lms.platform.eduverse.controllers;

import lms.platform.eduverse.models.Course;
import lms.platform.eduverse.models.Tutorial;
import lms.platform.eduverse.services.CourseService;
import lms.platform.eduverse.services.FileStorageService;
import lms.platform.eduverse.services.TutorialService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/tutorials")
public class TutorialController {

    @Value("${tutorials.images.path}")
    private String tutorialsImagesPath;

    private final UserService userService;

    private final TutorialService tutorialService;

    private final CourseService courseService;

    private final FileStorageService fileStorageService;

    @GetMapping
    @PreAuthorize("isAuthenticated() and @userService.isUserPremium(authentication) or hasRole('ROLE_ADMIN')")
    public String tutorials(Model model) {
        List<Course> courses = courseService.getAllCourses();

        // Передайте список случайных моделей в представление
        model.addAttribute("courses", courses);
        model.addAttribute("tutorials", tutorialService.getAllTutorials());
        return "tutorials/index";
    }

    @PostMapping("/store")
    @PreAuthorize("isAuthenticated() and @userService.isUserPremium(authentication) or hasRole('ROLE_ADMIN')")
    public String addTutorial(@RequestParam(name = "name") String name,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "image") MultipartFile image) {
        try {

            Tutorial tutorial = new Tutorial();

            tutorial.setName(name);
            tutorial.setDescription(description);
            tutorial.setIsActive(true);

            if (!image.isEmpty()) {
                String fileName = fileStorageService.saveFile(image, tutorialsImagesPath);
                tutorial.setImage(fileName);
            }

            Tutorial newTutorial = tutorialService.saveTutorial(tutorial);

            if (newTutorial != null) {
                return "redirect:/tutorials";
            } else {
                return "redirect:/tutorials?error";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/tutorials";
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @userService.isUserPremium(authentication) or hasAnyRole('ROLE_ADMIN')")
    public String tutorial(@PathVariable Long id, Model model) throws NoHandlerFoundException{
        if (tutorialService.getTutorialById(id) != null){
            Tutorial tutorial = tutorialService.getTutorialById(id);

            model.addAttribute("tutorial", tutorial);
            return "tutorials/single-tutorial";
        }
        else {
            throw new NoHandlerFoundException("GET", "/tutorials/" + id, null);
        }
    }


}