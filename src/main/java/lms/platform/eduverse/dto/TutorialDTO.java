package lms.platform.eduverse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TutorialDTO {

    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String name;
    private String description;
    private String image;
    private Boolean isActive;

}
