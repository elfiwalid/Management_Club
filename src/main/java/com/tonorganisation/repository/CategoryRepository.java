package com.tonorganisation.repository;
import com.tonorganisation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer>{

    Optional<Category> findByNomCategory(String nomCategory);
}
