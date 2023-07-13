package lms.platform.eduverse.mapper;

import lms.platform.eduverse.dto.TutorialDTO;
import lms.platform.eduverse.models.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TutorialMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    TutorialDTO toTutorialDTO(Tutorial tutorial);

    TutorialDTO toEntityTutorial(TutorialDTO tutorialDTO);

    List<TutorialDTO> toTutorialDTOList(List<Tutorial> tutorials);

    List<Tutorial> toEntityTutorialList(List<TutorialDTO> tutorialDTOs);
}
