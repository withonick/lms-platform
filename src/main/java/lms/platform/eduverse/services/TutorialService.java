package lms.platform.eduverse.services;

import lms.platform.eduverse.dto.TutorialDTO;
import lms.platform.eduverse.mapper.TutorialMapper;
import lms.platform.eduverse.models.Tutorial;
import lms.platform.eduverse.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    @Autowired
    private TutorialMapper tutorialMapper;

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }
    
    public Tutorial saveTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Tutorial getTutorialById(Long id) {
        return tutorialRepository.findById(id).orElse(null);
    }

    public void deleteTutorialById(Long id) {
        tutorialRepository.deleteById(id);
    }

    public List<TutorialDTO> getAllTutorialsDTO() {
        return tutorialMapper.toTutorialDTOList(tutorialRepository.findAll());
    }
}
