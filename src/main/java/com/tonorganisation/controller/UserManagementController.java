package com.tonorganisation.controller;

import com.tonorganisation.model.*;
import com.tonorganisation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private EntraineurRepository entraineurRepository;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // ------------------- JOUEUR -------------------
    @PostMapping("/joueurs")
    public ResponseEntity<?> createJoueur(@RequestBody Joueur joueur) {
        try {
            // Vérification des références
            if (!categoryRepository.existsById(joueur.getCategory().getIdCategory())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category ID non valide.");
            }

            if (userRepository.findByEmail(joueur.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà utilisé.");
            }

            // Création de l'utilisateur
            User user = new User();
            user.setEmail(joueur.getEmail());
            user.setPassword(joueur.getPassword());
            user.setRole(Role.JOUEUR);
            user = userRepository.save(user);

            // Liaison utilisateur -> joueur
            joueur.setUser(user);

            // Sauvegarde du joueur
            Joueur savedJoueur = joueurRepository.save(joueur);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedJoueur);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du joueur.");
        }
    }

    @GetMapping("/joueurs")
    public ResponseEntity<List<Joueur>> getAllJoueurs() {
        return ResponseEntity.ok(joueurRepository.findAll());
    }

    @PutMapping("/joueurs/{id}")
    public ResponseEntity<Joueur> updateJoueur(@PathVariable int id, @RequestBody Joueur updatedJoueur) {
        return joueurRepository.findById(id).map(joueur -> {
            joueur.setNom(updatedJoueur.getNom());
            joueur.setEmail(updatedJoueur.getEmail());
            joueur.setPassword(updatedJoueur.getPassword());
            joueur.setCategory(updatedJoueur.getCategory());
            joueurRepository.save(joueur);

            // Mise à jour de l'utilisateur
            User user = joueur.getUser();
            user.setEmail(updatedJoueur.getEmail());
            user.setPassword(updatedJoueur.getPassword());
            userRepository.save(user);

            return ResponseEntity.ok(joueur);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/joueurs/{id}")
    public ResponseEntity<Object> deleteJoueur(@PathVariable int id) {
        return joueurRepository.findById(id).map(joueur -> {
            userRepository.delete(joueur.getUser());
            joueurRepository.delete(joueur);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // ------------------- ENTRAINEUR -------------------
    @PostMapping("/entraineurs")
    public ResponseEntity<?> createEntraineur(@RequestBody Entraineur entraineur) {
        try {
            if (userRepository.findByEmail(entraineur.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà utilisé.");
            }

            // Création de l'utilisateur
            User user = new User();
            user.setEmail(entraineur.getEmail());
            user.setPassword(entraineur.getPassword());
            user.setRole(Role.ENTRAINEUR);
            user = userRepository.save(user);

            // Liaison utilisateur -> entraineur
            entraineur.setUser(user);

            // Sauvegarde de l'entraîneur
            Entraineur savedEntraineur = entraineurRepository.save(entraineur);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedEntraineur);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'entraîneur.");
        }
    }

    @GetMapping("/entraineurs")
    public ResponseEntity<List<Entraineur>> getAllEntraineurs() {
        return ResponseEntity.ok(entraineurRepository.findAll());
    }

    @PutMapping("/entraineurs/{id}")
    public ResponseEntity<Entraineur> updateEntraineur(@PathVariable int id, @RequestBody Entraineur updatedEntraineur) {
        return entraineurRepository.findById(id).map(entraineur -> {
            entraineur.setNom(updatedEntraineur.getNom());
            entraineur.setEmail(updatedEntraineur.getEmail());
            entraineur.setPassword(updatedEntraineur.getPassword());
            entraineurRepository.save(entraineur);

            // Mise à jour de l'utilisateur
            User user = entraineur.getUser();
            user.setEmail(updatedEntraineur.getEmail());
            user.setPassword(updatedEntraineur.getPassword());
            userRepository.save(user);

            return ResponseEntity.ok(entraineur);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/entraineurs/{id}")
    public ResponseEntity<Object> deleteEntraineur(@PathVariable int id) {
        return entraineurRepository.findById(id).map(entraineur -> {
            userRepository.delete(entraineur.getUser());
            entraineurRepository.delete(entraineur);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // ------------------- ADMINISTRATEUR -------------------
    @PostMapping("/administrateurs")
    public ResponseEntity<?> createAdministrateur(@RequestBody Administrateur administrateur) {
        try {
            if (userRepository.findByEmail(administrateur.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà utilisé.");
            }

            // Création de l'utilisateur
            User user = new User();
            user.setEmail(administrateur.getEmail());
            user.setPassword(administrateur.getPassword());
            user.setRole(Role.ADMINISTRATEUR);
            user = userRepository.save(user);

            // Liaison utilisateur -> administrateur
            administrateur.setUser(user);

            // Sauvegarde de l'administrateur
            Administrateur savedAdministrateur = administrateurRepository.save(administrateur);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdministrateur);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'administrateur.");
        }
    }

    @GetMapping("/administrateurs")
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        return ResponseEntity.ok(administrateurRepository.findAll());
    }

    @PutMapping("/administrateurs/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable int id, @RequestBody Administrateur updatedAdministrateur) {
        return administrateurRepository.findById(id).map(administrateur -> {
            administrateur.setNom(updatedAdministrateur.getNom());
            administrateur.setEmail(updatedAdministrateur.getEmail());
            administrateur.setPassword(updatedAdministrateur.getPassword());
            administrateurRepository.save(administrateur);

            // Mise à jour de l'utilisateur
            User user = administrateur.getUser();
            user.setEmail(updatedAdministrateur.getEmail());
            user.setPassword(updatedAdministrateur.getPassword());
            userRepository.save(user);

            return ResponseEntity.ok(administrateur);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/administrateurs/{id}")
    public ResponseEntity<Object> deleteAdministrateur(@PathVariable int id) {
        return administrateurRepository.findById(id).map(administrateur -> {
            userRepository.delete(administrateur.getUser());
            administrateurRepository.delete(administrateur);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
