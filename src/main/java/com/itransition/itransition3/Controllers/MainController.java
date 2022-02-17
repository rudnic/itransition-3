package com.itransition.itransition3.Controllers;

import com.itransition.itransition3.Entities.User;
import com.itransition.itransition3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String main(Model model, Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        userService.setLastOnline(user.getId(), "online");
        model.addAttribute("user", user);

        return "redirect:/users";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String processRegister(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);

        return "redirect:login";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/delete")
    public String deleteUsers(@RequestBody List<Long> listId) {
        userService.deleteAllById(listId);

        return "users";
    }

    @PutMapping("/users/block")
    public String blockUsers(@RequestBody List<Long> listId) {
        userService.blockAllById(listId);

        return "users";
    }

    @PutMapping("/users/unblock")
    public String unblockUsers(@RequestBody List<Long> listId) {
        userService.unblockAllById(listId);

        return "users";
    }

}
