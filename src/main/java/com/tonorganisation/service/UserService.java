package com.tonorganisation.service;

import com.tonorganisation.model.Joueur;
import com.tonorganisation.model.Role;
import com.tonorganisation.model.User;
import com.tonorganisation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Créer un utilisateur
    public User createUser(String email, String password, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Remplacez par un encodeur sécurisé en production
        user.setRole(role);
        return userRepository.save(user);
    }

    // Valider un utilisateur avec email et mot de passe
    public Optional<User> validateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    // Lier un joueur à un utilisateur existant
    public void assignPlayerToUser(Joueur joueur, User user) {
        joueur.setUser(user);
        userRepository.save(joueur);
    }

    public User authenticate(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }
}
