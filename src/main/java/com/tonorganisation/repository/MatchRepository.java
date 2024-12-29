package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository <Matches, Integer> {

    List<Matches> findByJoueur1 ( Joueur joueur1);
    List<Matches> findByJoueur2(Joueur joueur2);
    List <Matches> findByDateMatch ( LocalDate dateMatch);
    List<Matches> findByJoueur1IdJoueurOrJoueur2IdJoueur(int idJoueur, int idJoueur2);

    
}
