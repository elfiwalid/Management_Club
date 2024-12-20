package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match , Integer> {
    List<Match> findMatchByIdMatch(int idMatch);
    List<Match> findMatchByIdJoueur1(int idJoueur1);
    List<Match> findMatchByIdJoueur2(int idJoueur2);
    List<Match> findMatchByResultat(String resultat);
    
}
