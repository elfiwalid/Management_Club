package com.tonorganisation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private int idMatch;

    @Column(name = "date_match", nullable = false)
    private LocalDate dateMatch;

    @Column(name = "heure_match", nullable = false)
    private LocalTime heureMatch;

    @Column(name = "lieu", nullable = false)
    private String lieu;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_joueur1", nullable = false)
    private Joueur joueur1;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_joueur2", nullable = false)
    private Joueur joueur2;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_administrateur", nullable = true)
    @JsonIgnore
    private Administrateur administrateur;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_entraineur", nullable = true)
    @JsonIgnore
    private Entraineur entraineur;


    @Column(name = "resultat_joueur1", nullable = true, length = 10)
    private String resultatJoueur1;

    @Column(name = "resultat_joueur2", nullable = true, length = 10)
    private String resultatJoueur2;

    // Méthodes pour les noms des joueurs
    public String getJoueur1Nom() {
        return joueur1 != null ? joueur1.getNom() : null;
    }

    public String getJoueur2Nom() {
        return joueur2 != null ? joueur2.getNom() : null;
    }
}
