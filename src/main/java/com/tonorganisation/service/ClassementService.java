package com.tonorganisation.service;

import com.tonorganisation.model.Classement;
import com.tonorganisation.model.Joueur;
import com.tonorganisation.model.Matches;
import com.tonorganisation.model.Statistique;
import com.tonorganisation.repository.ClassementRepository;
import com.tonorganisation.repository.StatistiqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassementService {

    @Autowired
    private StatistiqueRepository statistiqueRepository;

    @Autowired
    private ClassementRepository classementRepository;

    public void mettreAJourStatistiquesEtClassement(Matches match) {
        Joueur joueur1 = match.getJoueur1();
        Joueur joueur2 = match.getJoueur2();
        
        // Vérifiez que les scores ne sont pas null
        if (match.getResultatJoueur1() == null || match.getResultatJoueur2() == null) {
            System.err.println("Les résultats des joueurs sont nuls. Mise à jour annulée.");
            return;
        }
    
        int score1 = Integer.parseInt(match.getResultatJoueur1());
        int score2 = Integer.parseInt(match.getResultatJoueur2());
    
        // Mettre à jour les statistiques des joueurs
        mettreAJourStatistique(joueur1, score1, score2);
        mettreAJourStatistique(joueur2, score2, score1);
    
        // Mettre à jour le classement global
        mettreAJourClassement();
    }
    
    private void mettreAJourStatistique(Joueur joueur, int scoreJoueur, int scoreAdversaire) {
        Statistique statistique = statistiqueRepository.findByJoueur(joueur);
    
        if (statistique == null) {
            statistique = new Statistique();
            statistique.setJoueur(joueur);
            statistique.setNbrMatchsJoues(0);
            statistique.setNbrMatchsGagnes(0);
            statistique.setNbrMatchsPerdus(0);
            statistique.setPointsTotaux(0);
        }
    
        // Mise à jour des statistiques
        statistique.setNbrMatchsJoues(statistique.getNbrMatchsJoues() + 1);
        if (scoreJoueur > scoreAdversaire) {
            statistique.setNbrMatchsGagnes(statistique.getNbrMatchsGagnes() + 1);
            statistique.setPointsTotaux(statistique.getPointsTotaux() + 3);
        } else if (scoreJoueur < scoreAdversaire) {
            statistique.setNbrMatchsPerdus(statistique.getNbrMatchsPerdus() + 1);
        } else {
            statistique.setPointsTotaux(statistique.getPointsTotaux() + 1); // Match nul
        }
    
        statistiqueRepository.save(statistique);
    }
    
    private void mettreAJourClassement() {
        List<Statistique> statistiques = statistiqueRepository.findAll();
        for (Statistique statistique : statistiques) {
            Classement classement = classementRepository.findByJoueur(statistique.getJoueur());
            if (classement == null) {
                classement = new Classement();
                classement.setJoueur(statistique.getJoueur());
                classement.setStatistique(statistique);
            }
            classement.setPoint(statistique.getPointsTotaux());
            classementRepository.save(classement);
        }
    }
    

    public List<Classement> getClassement() {
        return classementRepository.findAll();
    }

    public List<Classement> getClassementByPointsDesc() {
        return classementRepository.findAllByOrderByPointDesc();
    }
}
