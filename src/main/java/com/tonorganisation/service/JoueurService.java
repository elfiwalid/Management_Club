package com.tonorganisation.service;
import com.tonorganisation.repository.*;
import com.tonorganisation.model.*;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JoueurService {
    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private UserService userService;

    public Joueur createJoueur(String nom, LocalDate dateNaissance, String email, String password, Category category, Classement classement) {
        // Create user entry
        User user = userService.createUser(email, password, Role.JOUEUR);

        // Create joueur entry
        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setDateNaissance(dateNaissance);
        joueur.setEmail(email);
        joueur.setPassword(password);
        joueur.setCategory(category);
        joueur.setClassement(classement);
        joueur.setUser(user);

        return joueurRepository.save(joueur);
    }
}


