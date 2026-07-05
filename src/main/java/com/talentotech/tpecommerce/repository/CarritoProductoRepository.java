package com.talentotech.tpecommerce.repository;

import com.talentotech.tpecommerce.model.Carrito;
import com.talentotech.tpecommerce.model.CarritoProducto;
import com.talentotech.tpecommerce.model.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {
    
    // Busca si ya existe una fila con ese carrito y ese producto.
    // Si existe, incrementamos la cantidad. Si no, creamos una nueva fila.
    Optional<CarritoProducto> findByCarritoAndProducto(Carrito carrito, Producto producto);
    
}
