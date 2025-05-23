package com.tonorganisation.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "entrainement")
public class Entrainement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_entrainement")
    private int idEntrainement;

    @ManyToMany
    @JoinTable(
        name = "joueur_entrainement",  
        joinColumns = @JoinColumn(name = "id_entrainement"),
        inverseJoinColumns = @JoinColumn(name = "id_joueur")
    )  
    
    private List<Joueur> joueurs;

    @ManyToOne  
    @JoinColumn(name = "id_entraineur", nullable = false)  
    @JsonIgnore
    private Entraineur entraineur;

    @Column(name = "date_entrainement", nullable = false)
    private LocalDate dateEntrainement;

    @ManyToOne  
    @JoinColumn(name = "id_administrateur", nullable = false)  
    @JsonIgnore
    private Administrateur administrateur;

    @Column(name = "description")
    private String description;

}
