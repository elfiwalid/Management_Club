package com.tonorganisation.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "Administrateur")
public class Administrateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrateur")
    private int idAdministrateur;

    @Column(name = "nom", nullable = false, length = 255) 
    private String nom;

    @Column(name = "email", nullable = false, length = 255) 
    private String email;

    @Column(name = "password", nullable = false, length = 255) 
    private String password;


    @OneToMany(mappedBy = "administrateur")  
    private List<Match> matchs;

    @OneToMany(mappedBy = "administrateur")  
    private List<Entrainement> entrainements;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
