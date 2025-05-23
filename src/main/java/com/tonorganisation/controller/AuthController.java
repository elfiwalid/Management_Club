package com.tonorganisation.controller;

import com.tonorganisation.dto.LoginRequest;
import com.tonorganisation.dto.AuthResponse;
import com.tonorganisation.model.*;
import com.tonorganisation.repository.UserRepository;
import com.tonorganisation.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JoueurRepository joueurRepository; // Repository pour accéder aux joueurs

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Requête de connexion reçue pour : " + loginRequest.getEmail());

        return userRepository.findByEmail(loginRequest.getEmail())
                .map(user -> {
                    if (loginRequest.getPassword().equals(user.getPassword())) { // Utilisez `PasswordEncoder` en production pour sécuriser
                        AuthResponse response = new AuthResponse();
                        response.setMessage("Connexion réussie");
                        response.setRole(user.getRole().name());
                        response.setId(user.getId()); // ID de l'utilisateur

                        // Si le rôle est JOUEUR, ajouter idJoueur
                        if (user.getRole() == Role.JOUEUR) {
                            joueurRepository.findByUserId(user.getId()).ifPresent(joueur -> {
                                response.setJoueurId(joueur.getIdJoueur());
                            });
                        }

                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(401).body("Mot de passe incorrect");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Utilisateur non trouvé"));
    }
}
