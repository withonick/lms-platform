package lms.platform.eduverse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Boolean isFree;
    private Double rating;
    private Boolean isActive;
    private Boolean isPinned;

}
