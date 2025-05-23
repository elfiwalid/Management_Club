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

    public List<Matches> getMatchesByJoueur(int idJoueur) {
        return matchRepository.findMatchesByJoueur(idJoueur);
    }
        
    public Matches savedMatches(Matches match) {
        return matchRepository.save(match);
    }

    public List<Matches> getAllMatches() {
        return matchRepository.findAll();
    }

    public Matches getMatchById(int id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match introuvable avec l'ID : " + id));
    }

    public List<Matches> getMatchesByDate(LocalDate date) {
        return matchRepository.findByDateMatch(date);
    }

    public Matches updateMatchResult(int idMatch, String resultatJoueur1, String resultatJoueur2) {
        Matches match = matchRepository.findById(idMatch)
                .orElseThrow(() -> new RuntimeException("Match introuvable avec l'ID : " + idMatch));
    
        match.setResultatJoueur1(resultatJoueur1);
        match.setResultatJoueur2(resultatJoueur2);
    
        return matchRepository.save(match);
    }


    
}

