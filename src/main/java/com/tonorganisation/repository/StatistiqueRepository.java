package com.tonorganisation.repository;

import com.tonorganisation.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique, Integer> {

    List<Statistique> findByJoueur_IdJoueur(int idJoueur);
    List<Statistique> findByPointsTotauxGreaterThanEqual(int points);
    List<Statistique> findByNbrMatchsGagnesGreaterThanEqual(int nbrMatchsGagnes);
}
