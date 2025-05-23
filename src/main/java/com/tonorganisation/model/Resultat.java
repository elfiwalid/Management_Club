package com.tonorganisation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resultat")
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    private int idResultat;

    @OneToOne
    @JoinColumn(name = "id_match", nullable = false)
    private Matches matches;

    @Column(name = "resultat_joueur1", length = 10)
    private String resultatJoueur1;

    @Column(name = "resultat_joueur2", length = 10)
    private String resultatJoueur2;
}
