package com.tonorganisation.repository;

import com.tonorganisation.model.Entrainement;
import com.tonorganisation.model.Joueur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntrainementRepository extends JpaRepository<Entrainement, Integer> {

    @Query("SELECT e FROM Entrainement e JOIN e.joueurs j WHERE j.idJoueur = :idJoueur")
List<Entrainement> findEntrainementsByJoueurId(@Param("idJoueur") int idJoueur);

    List<Entrainement> findByDateEntrainement(LocalDate date);

    List<Entrainement> findByEntraineurIdEntraineur(int idEntraineur);

    List<Entrainement> findByAdministrateurIdAdministrateur(int idAdministrateur);

    List<Entrainement> findByDateEntrainementBetween(LocalDate startDate, LocalDate endDate);

}