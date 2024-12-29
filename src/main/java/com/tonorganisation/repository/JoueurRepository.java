package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;



@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Integer> {
}

