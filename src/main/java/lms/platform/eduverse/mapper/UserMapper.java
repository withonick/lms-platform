package lms.platform.eduverse.mapper;

import lms.platform.eduverse.dto.UserDTO;
import lms.platform.eduverse.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    UserDTO toUserDTO(UserDTO userDTO);

    User toEntityUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);

    List<User> toEntityUserList(List<UserDTO> userDTOS);
}