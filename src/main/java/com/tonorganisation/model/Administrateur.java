package com.tonorganisation.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private List<Match> matchs;

    @OneToMany(mappedBy = "administrateur")  
    @JsonIgnore
    private List<Entrainement> entrainements;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
