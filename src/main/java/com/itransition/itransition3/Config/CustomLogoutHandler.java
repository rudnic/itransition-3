package com.itransition.itransition3.Config;

import com.itransition.itransition3.Entities.User;
import com.itransition.itransition3.Repositories.UserRepository;
import com.itransition.itransition3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private UserService userService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        String now = DateTimeFormatter.ofPattern("MM/dd HH:mm").format(LocalDateTime.now());
        userService.setLastOnline(user.getId(), now);
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
