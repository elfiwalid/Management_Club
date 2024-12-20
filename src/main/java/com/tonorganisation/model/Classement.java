package com.tonorganisation.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Table(name = "classement")
public class Classement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classement")
    private int idClassement;

    @Column(name = "point",nullable = false)
    private int point;

    @ManyToOne
    @JoinColumn(name = "id_joueur", nullable = false) 
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "id_statistique", nullable = false) 
    private Statistique statistique;

}
