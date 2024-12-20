package com.tonorganisation.controller;

import com.tonorganisation.model.*;
import com.tonorganisation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/entrainement")
public class EntrainementController {

    @Autowired
    private  EntrainementService entrainementService;

    
    @PostMapping
    public List<Entrainement> saveEntrainement(Entrainement entrainement){
        return saveEntrainement(entrainement);
    }

    @GetMapping
    public  List<Entrainement> findEntrainements(){
        return entrainementService.getAllEntrainements();
    }

    @GetMapping("/date")
    public List<Entrainement> getEntrainementsByDate(@PathVariable LocalDate date) {
        return entrainementService.getEntrainementsByDate(date);
    }

    @DeleteMapping("/{id}")
    public String deleteEntrainement(@PathVariable int id) {
        entrainementService.deleteEntrainementById(id);
        return "Entrainement supprimé avec succès";
    }

    @PutMapping("/{id}")
    public Entrainement updateEntrainement(
            @PathVariable int id,
            @RequestBody Entrainement updatedEntrainement) {
        return entrainementService.getEntrainementById(id)
                .map(existingEntrainement -> {
                    updatedEntrainement.setIdEntrainement(existingEntrainement.getIdEntrainement());
                    return entrainementService.saveEntrainement(updatedEntrainement);
                })
                .orElse(null);
    }
    
    
    
}
