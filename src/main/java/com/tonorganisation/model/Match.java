package com.tonorganisation.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;

import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_match")
    private int idMatch;
    @Column(name = "date_matche", nullable = false)
    private LocalDate dateMatche;

    @Column(name = "heure_match", nullable = false)
    private LocalTime heureMatch;
    @Column(name = "lieu", nullable = false)
    private String lieu;

    @Column(name = "id_joueur1", length = 255, nullable = false)
    private int idJoueur1;
    @Column(name = "id_joueur2", length = 255, nullable = false)
    private int idJoueur2;
    @Column(name = "resultat", length = 255, nullable = true)
    private String resultat;


    @ManyToOne  
    @JoinColumn(name = "id_administrateur", nullable = false)  
    private Administrateur administrateur;

    @ManyToMany
    @JoinTable(
        name = "match_joueur",  
        joinColumns = @JoinColumn(name = "id_match"),
        inverseJoinColumns = @JoinColumn(name = "id_joueur")
    )
    private List<Joueur> joueurs;

    
}
