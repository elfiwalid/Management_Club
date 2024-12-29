package com.tonorganisation.repository;

import com.tonorganisation.model.Entrainement;
import com.tonorganisation.model.Joueur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntrainementRepository extends JpaRepository<Entrainement, Integer> {

    List<Entrainement> findByDateEntrainement(LocalDate date);

    List<Entrainement> findByEntraineurIdEntraineur(int idEntraineur);

    List<Entrainement> findByAdministrateurIdAdministrateur(int idAdministrateur);

    List<Entrainement> findByDateEntrainementBetween(LocalDate startDate, LocalDate endDate);

    List<Entrainement> findByJoueursContains(Joueur joueur);
}
