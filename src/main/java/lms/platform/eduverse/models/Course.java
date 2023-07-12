package lms.platform.eduverse.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_courses")
@Getter
@Setter
public class Course extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_premium")
    private Boolean isPremium;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany
    private List<User> users;

    public String loadImage(){
        if (image == null || image.isEmpty()) {
            return "/defaults/default.png";
        }
        return image;
    }

}
