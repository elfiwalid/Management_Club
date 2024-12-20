package com.tonorganisation.service;
import com.tonorganisation.repository.*;
import com.tonorganisation.model.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurService {
    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private UserService userService;

    public Administrateur createAdministrateur(String nom, String email, String password) {
        // Create user entry
        User user = userService.createUser(email, password, Role.ADMINISTRATEUR);

        // Create administrateur entry
        Administrateur administrateur = new Administrateur();
        administrateur.setNom(nom);
        administrateur.setEmail(email);
        administrateur.setPassword(password);
        administrateur.setUser(user);

        return administrateurRepository.save(administrateur);
    }
}
