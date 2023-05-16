package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional(readOnly = true)
    Optional<Category> findByName(String name);
}
