package lms.platform.eduverse.mapper;

import lms.platform.eduverse.dto.CourseDTO;
import lms.platform.eduverse.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    CourseDTO toCourseDTO(CourseDTO courseDTO);

    CourseDTO toEntityCourse(CourseDTO courseDTO);

    List<CourseDTO> toCourseDTOList(List<Course> courses);

    List<Course> toEntityCourseList(List<CourseDTO> courseDTOS);
}
