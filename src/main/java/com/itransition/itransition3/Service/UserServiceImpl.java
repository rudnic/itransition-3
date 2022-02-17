package com.itransition.itransition3.Service;

import com.itransition.itransition3.Entities.User;
import com.itransition.itransition3.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean saveUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void setLastOnline(Long id, String time) {
        userRepository.setLastOnline(id, time);
    }

    @Override
    public boolean deleteAllById(List<Long> listId) {
        userRepository.deleteAllById(listId);
        return true;
    }

    @Override
    public boolean blockAllById(List<Long> listId) {
        for (Long id : listId) {
            userRepository.blockAcc(id);
        }
        return true;
    }

    @Override
    public boolean unblockAllById(List<Long> listId) {
        for (Long id : listId) {
            userRepository.unblockAcc(id);
        }
        return true;
    }
}
