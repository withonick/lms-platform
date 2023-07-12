package lms.platform.eduverse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_tutorials")
@Getter
@Setter

public class Tutorial extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "is_active")
    private Boolean isActive;

    public String loadImage(){
        if (image == null || image.isEmpty()) {
            return "/defaults/default.png";
        }
        return image;
    }

}
