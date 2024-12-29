package com.tonorganisation.service;

import com.tonorganisation.model.Entrainement;
import com.tonorganisation.model.Joueur;
import com.tonorganisation.repository.EntrainementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntrainementService {

    private final EntrainementRepository entrainementRepository;

    @Autowired
    public EntrainementService(EntrainementRepository entrainementRepository) {
        this.entrainementRepository = entrainementRepository;
    }

    public Entrainement saveEntrainement(Entrainement entrainement) {
        return entrainementRepository.save(entrainement);
    }

    public Optional<Entrainement> getEntrainementById(int id) {
        return entrainementRepository.findById(id);
    }

    public List<Entrainement> getAllEntrainements() {
        return entrainementRepository.findAll();
    }

    public void deleteEntrainementById(int id) {
        entrainementRepository.deleteById(id);
    }

    public List<Entrainement> getEntrainementsByDate(LocalDate date) {
        return entrainementRepository.findByDateEntrainement(date);
    }

    public List<Entrainement> getEntrainementsByEntraineur(int idEntraineur) {
        return entrainementRepository.findByEntraineurIdEntraineur(idEntraineur);
    }

    public List<Entrainement> getEntrainementsByAdministrateur(int idAdministrateur) {
        return entrainementRepository.findByAdministrateurIdAdministrateur(idAdministrateur);
    }

    public List<Entrainement> getEntrainementsByDateRange(LocalDate startDate, LocalDate endDate) {
        return entrainementRepository.findByDateEntrainementBetween(startDate, endDate);
    }


    public List<Entrainement> findEntrainementByJoueur(Joueur joueur) {
    return entrainementRepository.findByJoueursContains(joueur);
    }

}
