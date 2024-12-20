package com.tonorganisation.service;

import com.tonorganisation.repository.CategoryRepository;
import com.tonorganisation.model.Category;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(int idCategory) {
        return categoryRepository.findById(idCategory);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category) {
        if (categoryRepository.existsById(category.getIdCategory())) {
            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("La catégorie avec l'ID " + category.getIdCategory() + " n'existe pas.");
        }
    }

    // Supprimer une catégorie par son ID
    public void deleteCategory(int idCategory) {
        if (categoryRepository.existsById(idCategory)) {
            categoryRepository.deleteById(idCategory);
        } else {
            throw new IllegalArgumentException("La catégorie avec l'ID " + idCategory + " n'existe pas.");
        }
    }

    // Trouver une catégorie par son nom
    public Optional<Category> findCategoryByNom(String nomCategory) {
        return categoryRepository.findByNomCategory(nomCategory);
    }
}
