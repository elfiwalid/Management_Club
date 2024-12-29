package com.tonorganisation.controller;

import com.tonorganisation.model.*;
import com.tonorganisation.service.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // Créer un nouveau match
    @PostMapping
    public ResponseEntity<?> createMatch(@RequestBody Matches match) {
        try {
            // Sauvegarder le match
            Matches savedMatch = matchService.savedMatches(match);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du match.");
        }
    }

    @GetMapping("/joueur/{idJoueur}")
    public ResponseEntity<List<Matches>> getMatchesByJoueur(@PathVariable int idJoueur) {
        List<Matches> matches = matchService.getMatchesByJoueur(idJoueur);
        if (matches.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            return ResponseEntity.ok(matches);
    }

    // Récupérer tous les matchs
    @GetMapping
    public ResponseEntity<List<Matches>> getAllMatches() {
        try {
            List<Matches> matches = matchService.getAllMatches();
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Récupérer un match par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getMatchById(@PathVariable int id) {
        try {
            Matches match = matchService.getMatchById(id);
            if (match != null) {
                return ResponseEntity.ok(match);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match non trouvé.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération du match.");
        }
    }

    // Récupérer les matchs par joueur 1
    @GetMapping("/joueur1/{id}")
    public ResponseEntity<?> getMatchesByJoueur1(@PathVariable int id) {
        try {
            Joueur joueur1 = new Joueur();
            joueur1.setIdJoueur(id);
            List<Matches> matches = matchService.getMatchesByJoueur1(joueur1);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des matchs.");
        }
    }

    // Récupérer les matchs par joueur 2
    @GetMapping("/joueur2/{id}")
    public ResponseEntity<?> getMatchesByJoueur2(@PathVariable int id) {
        try {
            Joueur joueur2 = new Joueur();
            joueur2.setIdJoueur(id);
            List<Matches> matches = matchService.getMatchesByJoueur2(joueur2);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des matchs.");
        }
    }

    // Récupérer les matchs par date
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getMatchesByDate(@PathVariable String date) {
        try {
            LocalDate dateMatch = LocalDate.parse(date);
            List<Matches> matches = matchService.getMatchesByDate(dateMatch);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Format de date invalide.");
        }
    }
}
