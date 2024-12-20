package com.tonorganisation.service;

import com.tonorganisation.repository.MatchRepository;
import com.tonorganisation.model.*;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    public Match matchsave(Match match){
        return matchRepository.save(match);
    }
    public List<Match> findMatchsAll(){
        return matchRepository.findAll();
    }

    public List<Match> findMatchByid(int idMatch){
        return matchRepository.findMatchByIdMatch(idMatch);
    }
    public List<Match> findMatchByIdJoueur1(int idJoueur1){
        return matchRepository.findMatchByIdJoueur1(idJoueur1);
    }
    public List<Match> findMatchByIdJoueur2(int idJoueur2){
        return matchRepository.findMatchByIdJoueur2(idJoueur2);
    }
    public List<Match> findMatchByResultat(String resultat){
        return matchRepository.findMatchByResultat(resultat);
    }
    
}
