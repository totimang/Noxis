package com.talentotech.tpecommerce.service;

import com.talentotech.tpecommerce.repository.ProductoRepository;
//import com.talentotech.tpecommerce.exception.PrecioInvalidoException;
import com.talentotech.tpecommerce.exception.ProductoNoEncontradoException;
//import com.talentotech.tpecommerce.exception.StockInsuficienteException;
import com.talentotech.tpecommerce.model.Producto;
//import com.techlab.ecommerce.util.Validador;
import org.springframework.stereotype.Service;
//import java.util.ArrayList;
import java.util.List;


@Service
public class ProductoService {

    private final ProductoRepository repository;


    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }


    // CREATE: agrega un nuevo producto al catálogo.
    public Producto guardar(Producto p) {
        
        /*if (p.getNombre() == null || p.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (p.getPrecio() <=0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió; " + p.getPrecio());
        }
        if (p.getStock() <0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + p.getStock());
        }*/
        

        return repository.save(p);
    }


    public List<Producto> listarTodos() {
        return repository.findAll();
    }


    public Producto obtenerPorId(Integer id) {
        
        return repository.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("No se encontró un producto con id " + id));
        
    }

    public Producto actualizar(Integer id, Producto datos) {
        
        
        /*if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (datos.getPrecio() <=0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió; " + datos.getPrecio());
        }
        if (datos.getStock() <0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + datos.getStock());
        }*/
        

        Producto p = obtenerPorId(id);

        // Validamos los nuevos datos antes de aplicarlos.
        /*Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());*/

        // Modificamos el producto encontrado. Como Java pasa los
        // objetos por referencia, los cambios se reflejan en la
        // lista sin necesidad de hacer nada más.
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());
        
        if (datos.getImagenUrl() != null) {
            p.setImagenUrl(datos.getImagenUrl());
        };

        return repository.save(p);
    }

    // DELETE: elimina un producto por id.
    public void eliminar(Integer id) {
        // Verificamos que exista antes de eliminar. Si no existe,
        // obtenerPorId lanza la excepción y el método termina.
        Producto p = obtenerPorId(id);
        repository.delete(p);
    }
    
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }
    
    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }
    
}
