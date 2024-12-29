package com.tonorganisation.controller;

import com.tonorganisation.model.*;
import com.tonorganisation.repository.EntrainementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrainements")
public class EntrainementController {

    @Autowired
    private EntrainementRepository entrainementRepository;

    // Créer un nouvel entraînement
    @PostMapping
    public ResponseEntity<Entrainement> createEntrainement(@RequestBody Entrainement entrainement) {
        try {
            Entrainement savedEntrainement = entrainementRepository.save(entrainement);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEntrainement);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Récupérer tous les entraînements
    @GetMapping
    public ResponseEntity<List<Entrainement>> getAllEntrainements() {
        try {
            List<Entrainement> entrainements = entrainementRepository.findAll();
            return ResponseEntity.ok(entrainements);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Récupérer un entraînement par ID
    // Récupérer un entraînement par ID
@GetMapping("/{id}")
public ResponseEntity<?> getEntrainementById(@PathVariable int id) {
    Optional<Entrainement> entrainement = entrainementRepository.findById(id);
    
    if (entrainement.isPresent()) {
        return ResponseEntity.ok(entrainement.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entraînement non trouvé");
    }
}
@GetMapping("/joueur/{idJoueur}")
public ResponseEntity<List<Entrainement>> getEntrainementsByJoueur(@PathVariable int idJoueur) {
    Joueur joueur = new Joueur();
    joueur.setIdJoueur(idJoueur); // Créer un objet Joueur avec l'ID fourni

    List<Entrainement> entrainements = entrainementRepository.findByJoueursContains(joueur);

    System.out.println("Entraînements trouvés : " + entrainements.size()); // Debug

    return entrainements.isEmpty() 
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        : ResponseEntity.ok(entrainements);
}


    // Récupérer les entraînements par date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Entrainement>> getEntrainementsByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Entrainement> entrainements = entrainementRepository.findByDateEntrainement(localDate);
            return ResponseEntity.ok(entrainements);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Récupérer les entraînements par entraîneur
    @GetMapping("/entraineur/{idEntraineur}")
    public ResponseEntity<List<Entrainement>> getEntrainementsByEntraineur(@PathVariable int idEntraineur) {
        List<Entrainement> entrainements = entrainementRepository.findByEntraineurIdEntraineur(idEntraineur);
        return ResponseEntity.ok(entrainements);
    }

    // Récupérer les entraînements par administrateur
    @GetMapping("/administrateur/{idAdministrateur}")
    public ResponseEntity<List<Entrainement>> getEntrainementsByAdministrateur(@PathVariable int idAdministrateur) {
        List<Entrainement> entrainements = entrainementRepository.findByAdministrateurIdAdministrateur(idAdministrateur);
        return ResponseEntity.ok(entrainements);
    }

    // Récupérer les entraînements par plage de dates
    @GetMapping("/dates")
    public ResponseEntity<List<Entrainement>> getEntrainementsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            List<Entrainement> entrainements = entrainementRepository.findByDateEntrainementBetween(start, end);
            return ResponseEntity.ok(entrainements);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Mettre à jour un entraînement
    @PutMapping("/{id}")
public ResponseEntity<?> updateEntrainement(@PathVariable int id, @RequestBody Entrainement updatedEntrainement) {
    Optional<Entrainement> optionalEntrainement = entrainementRepository.findById(id);
    
    if (optionalEntrainement.isPresent()) {
        Entrainement entrainement = optionalEntrainement.get();
        entrainement.setDateEntrainement(updatedEntrainement.getDateEntrainement());
        entrainement.setDescription(updatedEntrainement.getDescription());
        entrainement.setJoueurs(updatedEntrainement.getJoueurs());
        entrainement.setEntraineur(updatedEntrainement.getEntraineur());
        entrainement.setAdministrateur(updatedEntrainement.getAdministrateur());
        Entrainement savedEntrainement = entrainementRepository.save(entrainement);
        return ResponseEntity.ok(savedEntrainement);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entraînement non trouvé");
    }
}



    // Supprimer un entraînement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntrainement(@PathVariable int id) {
        try {
            if (entrainementRepository.existsById(id)) {
                entrainementRepository.deleteById(id);
                return ResponseEntity.ok("Entraînement supprimé avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entraînement non trouvé");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression");
        }
    }
}
