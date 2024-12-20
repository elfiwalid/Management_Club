package com.tonorganisation.repository;

import com.tonorganisation.model.Classement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassementRepository extends JpaRepository<Classement, Integer> {

    // Rechercher par le nombre de points
    List<Classement> findByPointGreaterThanEqual(int points);

    // Trouver les classements associés à un joueur spécifique
    List<Classement> findByJoueur_IdJoueur(int idJoueur);

    // Trouver les classements liés à une statistique spécifique
    List<Classement> findByStatistique_IdStatistique(int idStatistique);
}
