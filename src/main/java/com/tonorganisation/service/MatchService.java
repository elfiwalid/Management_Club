package com.tonorganisation.service;

import com.tonorganisation.repository.MatchRepository;
import com.tonorganisation.model.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Matches savedMatches(Matches matches) {
        // Validation des joueurs
        if (matches.getJoueur1() == null || matches.getJoueur1().getIdJoueur() == 0) {
            throw new RuntimeException("Joueur 1 est obligatoire.");
        }
        if (matches.getJoueur2() == null || matches.getJoueur2().getIdJoueur() == 0) {
            throw new RuntimeException("Joueur 2 est obligatoire.");
        }

        // Validation de la date et de l'heure
        if (matches.getDateMatch() == null || matches.getHeureMatch() == null) {
            throw new RuntimeException("La date et l'heure du match sont obligatoires.");
        }
        

        return matchRepository.save(matches);
    }


    public List<Matches> getAllMatches() {
        return matchRepository.findAll();
    }

    public Matches getMatchById(int id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match non trouvé avec l'ID : " + id));
    }

    public List<Matches> getMatchesByJoueur1(Joueur joueur1) {
        return matchRepository.findByJoueur1(joueur1);
    }

    public List<Matches> getMatchesByJoueur2(Joueur joueur2) {
        return matchRepository.findByJoueur2(joueur2);
    }

    public List<Matches> getMatchesByDate(LocalDate dateMatch) {
        return matchRepository.findByDateMatch(dateMatch);
    }

    public List<Matches> getMatchesByJoueur(int idJoueur) {
        return matchRepository.findByJoueur1IdJoueurOrJoueur2IdJoueur(idJoueur, idJoueur);
    }
}
