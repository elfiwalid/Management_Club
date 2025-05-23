package com.tonorganisation.controller;

import com.tonorganisation.model.*;
import com.tonorganisation.repository.*;
import com.tonorganisation.service.*;

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
    private  MatchRepository matchRepository;
    private ClassementService classementService;

    @Autowired
    public MatchController(MatchService matchService, ClassementService classementService) {
        this.matchService = matchService;
        this.classementService = classementService;
        
        if (this.classementService == null) {
        System.err.println("ClassementService is null in MatchController!");
    }
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
public ResponseEntity<?> getMatchById(@PathVariable("id") int id) {
    try {
        Matches match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération du match.");
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

    @PutMapping("/{id}")
public ResponseEntity<?> updateMatchResult(
        @PathVariable("id") int id,
        @RequestBody Matches updatedMatch
) {
    try {
        Matches match = matchService.updateMatchResult(
                id,
                updatedMatch.getResultatJoueur1(),
                updatedMatch.getResultatJoueur2()
        );
        // Mettre à jour les statistiques et le classement
        classementService.mettreAJourStatistiquesEtClassement(match);

        return ResponseEntity.ok(match);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du match.");
    }
}


    @GetMapping("/joueur/{idJoueur}")
public ResponseEntity<List<Matches>> getMatchesByJoueur(@PathVariable("idJoueur") int idJoueur) {
    try {
        System.out.println("Recherche des matchs pour le joueur avec ID : " + idJoueur);
        List<Matches> matches = matchService.getMatchesByJoueur(idJoueur);
        System.out.println("Nombre de matchs trouvés : " + matches.size());

        if (matches.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(matches);
    } catch (Exception e) {
        e.printStackTrace(); // Log de l'exception pour diagnostic
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}


}