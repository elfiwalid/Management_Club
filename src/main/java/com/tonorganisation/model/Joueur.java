package com.tonorganisation.model;
import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "joueur") 
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_joueur")  
    private int idJoueur;  
    @Column(name = "nom", length = 255, nullable = false) 
    private String nom;

    @Column(name = "date_naissance", nullable = false) 
    private LocalDate dateNaissance;

    @Column(name = "email", length = 255, nullable = false) 
    private String email;

    @Column(name = "password", length = 255, nullable = false)  
    private String password;

    @ManyToMany
    @JoinTable(
        name = "joueur_entrainement", 
        joinColumns = @JoinColumn(name = "id_joueur"), 
        inverseJoinColumns = @JoinColumn(name = "id_entrainement") 
    )
    private List<Entrainement> entrainements; 

    @ManyToMany
    @JoinTable(
        name = "match_joueur",  
        joinColumns = @JoinColumn(name = "id_joueur"),
        inverseJoinColumns = @JoinColumn(name = "id_match")
    )
    private List<Match> matchs;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_classement", nullable = true) 
    private Classement classement;

    @OneToOne(mappedBy = "joueur") 
    private Statistique statistique;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}
