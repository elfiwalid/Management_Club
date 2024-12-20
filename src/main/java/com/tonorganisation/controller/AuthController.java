package com.tonorganisation.controller;

import com.tonorganisation.dto.LoginRequest;
import com.tonorganisation.dto.LoginResponse;
import com.tonorganisation.model.*;
import com.tonorganisation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Requête de connexion reçue pour : " + loginRequest.getEmail());

        return userRepository.findByEmail(loginRequest.getEmail())
                .map(user -> {
                    if (loginRequest.getPassword().equals(user.getPassword())) {
                        LoginResponse response = new LoginResponse();
                        response.setMessage("Connexion réussie");
                        response.setRole(user.getRole().name());
                        return ResponseEntity.ok(response);
                    } else {
                        return ResponseEntity.status(401).body("Mot de passe incorrect");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Utilisateur non trouvé"));
    }
}
