package lms.platform.eduverse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_lessons")
@Getter
@Setter
public class Lesson extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "video")
    private String video;

    @Column(name = "is_active")
    private Boolean isActive;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
