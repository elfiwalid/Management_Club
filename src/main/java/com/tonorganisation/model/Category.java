package com.tonorganisation.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int idCategory;

    @Column(name = "nom_category", nullable = false, length = 255) 
    private String nomCategory;

    @OneToMany(mappedBy = "category")
    private List<Joueur> joueurs;

}
