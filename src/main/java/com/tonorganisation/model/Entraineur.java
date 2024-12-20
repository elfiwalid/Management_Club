package com.tonorganisation.model;
import jakarta.persistence.*;
import java.util.*;


import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "entraineur") 

public class Entraineur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_entraineur")
    private int idEntraineur;

    @Column(name = "nom", length = 255, nullable = false)
    private String nom;

    @Column(name = "email", length = 255, nullable = false)
    private String email;
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @OneToMany(mappedBy = "entraineur")  
    private List<Entrainement> entrainements;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
