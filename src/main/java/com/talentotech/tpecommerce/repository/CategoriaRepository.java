package com.talentotech.tpecommerce.repository;

import com.talentotech.tpecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Integer> {
    
}
