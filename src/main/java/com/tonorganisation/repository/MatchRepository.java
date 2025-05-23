package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository <Matches, Integer> {

    @Query("SELECT m FROM Matches m WHERE m.joueur1.idJoueur = :idJoueur OR m.joueur2.idJoueur = :idJoueur")
    List<Matches> findMatchesByJoueur(@Param("idJoueur") int idJoueur);

    List<Matches> findByJoueur1 ( Joueur joueur1);
    List<Matches> findByJoueur2(Joueur joueur2);
    List <Matches> findByDateMatch ( LocalDate dateMatch);
}
