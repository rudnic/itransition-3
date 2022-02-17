package com.itransition.itransition3.Service;

import com.itransition.itransition3.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    public User getByUsername(String username);
    public User getUserById(Long id);
    public boolean saveUser(User user);
    public List<User> getAllUsers();
    public void setLastOnline(Long id, String time);
    public boolean deleteAllById(List<Long> listId);
    public boolean blockAllById(List<Long> listId);
    public boolean unblockAllById(List<Long> listId);
}
