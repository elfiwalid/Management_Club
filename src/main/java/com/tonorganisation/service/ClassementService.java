package com.tonorganisation.service;

import com.tonorganisation.model.Classement;
import com.tonorganisation.repository.ClassementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassementService {

    private final ClassementRepository classementRepository;

    @Autowired
    public ClassementService(ClassementRepository classementRepository) {
        this.classementRepository = classementRepository;
    }

    public Classement saveClassement(Classement classement) {
        return classementRepository.save(classement);
    }

    public Optional<Classement> findById(int idClassement) {
        return classementRepository.findById(idClassement);
    }

    public void deleteById(int idClassement) {
        if (classementRepository.existsById(idClassement)) {
            classementRepository.deleteById(idClassement);
        } else {
            throw new IllegalArgumentException("Classement avec l'ID " + idClassement + " n'existe pas.");
        }
    }

    public List<Classement> findAll() {
        return classementRepository.findAll();
    }

    public List<Classement> findByPointsGreaterThanEqual(int points) {
        return classementRepository.findByPointGreaterThanEqual(points);
    }

    public List<Classement> findByJoueurId(int idJoueur) {
        return classementRepository.findByJoueur_IdJoueur(idJoueur);
    }

    public List<Classement> findByStatistiqueId(int idStatistique) {
        return classementRepository.findByStatistique_IdStatistique(idStatistique);
    }
}
