package com.tonorganisation.controller;


import com.tonorganisation.model.*;
import com.tonorganisation.repository.*;
import com.tonorganisation.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private JoueurRepository joueurRepository;
    @Autowired
    private EntraineurRepository entraineurRepository;
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/total-joueurs")
    public ResponseEntity<Long> getTotalJoueurs() {
        return ResponseEntity.ok(joueurRepository.count());
    }

    @GetMapping("/total-entraineurs")
    public ResponseEntity<Long> getTotalEntraineurs() {
        return ResponseEntity.ok(entraineurRepository.count());
    }

    @GetMapping("/total-matches")
    public ResponseEntity<Long> getTotalMatches() {
        return ResponseEntity.ok(matchRepository.count());
    }

    @GetMapping("/match-monthly-stats")
public ResponseEntity<List<Map<String, Object>>> getMonthlyMatchStats() {
    List<Map<String, Object>> monthlyStats = new ArrayList<>();

    // Exemple de calcul des statistiques par mois
    List<Matches> matches = matchRepository.findAll();

    Map<String, int[]> statsByMonth = new HashMap<>();

    for (Matches match : matches) {
        String month = match.getDateMatch().getMonth().name();

        statsByMonth.putIfAbsent(month, new int[]{0, 0}); // {Gagnés, Perdus}

        int score1 = Integer.parseInt(match.getResultatJoueur1());
        int score2 = Integer.parseInt(match.getResultatJoueur2());

        if (score1 > score2) {
            statsByMonth.get(month)[0]++; // Gagnés pour joueur1
        } else if (score2 > score1) {
            statsByMonth.get(month)[1]++; // Gagnés pour joueur2
        }
    }

    statsByMonth.forEach((month, stats) -> {
        monthlyStats.add(Map.of(
            "month", month,
            "won", stats[0],
            "lost", stats[1]
        ));
    });

    return ResponseEntity.ok(monthlyStats);
}

}
