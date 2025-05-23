package com.tonorganisation.service;

import com.tonorganisation.model.Statistique;
import com.tonorganisation.repository.StatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatistiqueService {

    private final StatistiqueRepository statistiqueRepository;

    @Autowired
    public StatistiqueService(StatistiqueRepository statistiqueRepository) {
        this.statistiqueRepository = statistiqueRepository;
    }

    public Statistique saveStatistique(Statistique statistique) {
        return statistiqueRepository.save(statistique);
    }

    public Optional<Statistique> findById(int idStatistique) {
        return statistiqueRepository.findById(idStatistique);
    }

    public List<Statistique> findAll() {
        return statistiqueRepository.findAll();
    }

    public void deleteById(int idStatistique) {
        if (statistiqueRepository.existsById(idStatistique)) {
            statistiqueRepository.deleteById(idStatistique);
        } else {
            throw new IllegalArgumentException("Statistique avec l'ID " + idStatistique + " n'existe pas.");
        }
    }

}
