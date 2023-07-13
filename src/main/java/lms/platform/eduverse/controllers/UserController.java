package lms.platform.eduverse.controllers;

import lms.platform.eduverse.models.User;
import lms.platform.eduverse.services.FileStorageService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Value("${users.images.path}")
    private String userimagesPath;

    private final UserService userService;

    private final FileStorageService fileStorageService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public String updateUser(@RequestParam(name = "firstname") String firstName,
                             @RequestParam(name = "lastname") String lastName,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "image")MultipartFile avatar){

        try{
            User user = userService.getCurrentSessionUser();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);

            if (!avatar.isEmpty()){
                String imagePath = fileStorageService.saveFile(avatar, userimagesPath);
                user.setAvatar(imagePath);
            }
            userService.saveUser(user);

            return "redirect:/profile?success";
        }
        catch (IOException e){
            e.printStackTrace();
            return "redirect:/profile?error";
        }
    }

}
