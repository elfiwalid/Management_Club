package com.tonorganisation.repository;

import com.tonorganisation.model.Classement;
import com.tonorganisation.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassementRepository extends JpaRepository<Classement, Integer> {
    Classement findByJoueur(Joueur joueur);
    List<Classement> findByPointGreaterThanEqual(int points);
    List<Classement> findAllByOrderByPointDesc();
}
