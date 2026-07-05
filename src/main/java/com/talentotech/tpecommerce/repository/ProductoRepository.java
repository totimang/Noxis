package com.talentotech.tpecommerce.repository;

import com.talentotech.tpecommerce.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository <Producto, Integer> {
    
    List<Producto> findByNombreContaining (String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.categoria = :nombreCategoria")
    List<Producto> buscarPorCategoria (@Param("nombreCategoria") String nombreCategoria);
    
}
