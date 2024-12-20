package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntraineurRepository extends JpaRepository<Entraineur, Integer> {}

