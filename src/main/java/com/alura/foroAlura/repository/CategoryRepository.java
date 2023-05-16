package com.alura.foroAlura.repository;

import com.alura.foroAlura.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
