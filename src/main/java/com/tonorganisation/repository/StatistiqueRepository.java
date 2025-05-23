package com.tonorganisation.repository;

import com.tonorganisation.model.Joueur;
import com.tonorganisation.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatistiqueRepository extends JpaRepository<Statistique, Integer> {
    Statistique findByJoueur(Joueur joueur);
}
