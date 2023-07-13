package lms.platform.eduverse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String avatar;
    private Boolean isPremium;

    public String getAvatar() {
        if (avatar == null){
            return "/defaults/avatar.png";
        }
        else {
            return avatar;
        }
    }
}
