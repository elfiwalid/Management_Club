package com.tonorganisation.service;
import com.tonorganisation.repository.*;
import com.tonorganisation.model.*;
import com.tonorganisation.model.User;
import com.tonorganisation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Add encryption in production
        user.setRole(role);
        return userRepository.save(user);
    }
}

