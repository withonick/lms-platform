package lms.platform.eduverse.controllers;

import lms.platform.eduverse.models.User;
import lms.platform.eduverse.services.PermissionService;
import lms.platform.eduverse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PermissionService permissionService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/sign-in")
    public String signIn(){
        return "auth/login";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "auth/register";
    }

    @PostMapping("/to-sign-up")
    public String toSignUp(@RequestParam(name = "user_firstname") String firstName,
                           @RequestParam(name = "user_lastname") String lastName,
                           @RequestParam(name = "user_lastname") String username,
                           @RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_repeat_password") String repeat_password
                           ){
        if (password.equals(repeat_password)){
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setIsPremium(false);
            user.setPassword(password);
            User newUser = userService.addUser(user);

            if (newUser != null){
                newUser.getPermissions().add(permissionService.userRolePermission());
                return "redirect:/sign-up?success";
            }
            else{
                return "redirect:/sign-up?error";
            }
        }
        else{
            return "redirect:/sign-up?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("user", userService.getCurrentSessionUser());
        return "profile";
    }

}
