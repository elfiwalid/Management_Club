package com.tonorganisation.model;
import jakarta.persistence.*;


import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "statistique")
public class Statistique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_statistique")
    private int idStatistique;
    @Column(name = "nbr_matchs_joues", length = 255, nullable = false)
    private int nbrMatchsJoues;
    @Column(name = "nbr_matchs_gagnes", length = 255, nullable = false)
    private int nbrMatchsGagnes;
    @Column(name = "nbr_matchs_perdus", length = 255, nullable = false)
    private int nbrMatchsPerdus;
    @Column(name = "points_totaux", length = 255, nullable = false)
    private int pointsTotaux;
    @ManyToOne  
    @JoinColumn(name = "id_joueur", nullable = false) 
    private Joueur joueur;  
    
}
