package lms.platform.eduverse.mapper;

import lms.platform.eduverse.dto.CourseDTO;
import lms.platform.eduverse.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO convertToDto(Course course);

    Course convertToEntity(CourseDTO courseDTO);

    List<CourseDTO> convertToDtoList(List<Course> courses);

    List<Course> convertToEntityList(List<CourseDTO> courseDTOs);

}
