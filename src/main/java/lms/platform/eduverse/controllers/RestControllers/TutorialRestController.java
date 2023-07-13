package lms.platform.eduverse.controllers.RestControllers;

import lms.platform.eduverse.dto.CourseDTO;
import lms.platform.eduverse.services.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tutorial")
@RequiredArgsConstructor
public class TutorialRestController {

    private final TutorialService tutorialService;


    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable("id") Long tutorialId) {
        try {
            tutorialService.deleteTutorialById(tutorialId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
