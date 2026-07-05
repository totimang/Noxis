package com.talentotech.tpecommerce.controller;

import com.talentotech.tpecommerce.exception.ProductoNoEncontradoException;
import com.talentotech.tpecommerce.model.Producto;
import com.talentotech.tpecommerce.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    private final ProductoService service;
    
    public ProductoController(ProductoService service) {
        this.service = service;
    }    
    
    /*
    @GetMapping
    public List<Producto> listarTodos() {
        return service.listarTodos();
    }
    
    
    @GetMapping("/{id}")
    public Producto obtenerProducto (@PathVariable int id){
        return service.obtenerPorId(id);
    }
    
    @PostMapping("")
    public Producto crearProducto(@RequestBody Producto producto){
        return service.guardar(producto);
    }
    
    @PutMapping("{id}")
    public Producto actualizar(@PathVariable int id, @RequestBody Producto datos) {
        return service.actualizar(id, datos);
    }
    
    @DeleteMapping("{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }*/
    
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto (@PathVariable int id){

            return ResponseEntity.ok(service.obtenerPorId(id));
            
    }
    
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto nuevoProducto){
        Producto creado = service.guardar(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @Valid @RequestBody Producto datos) {

            return ResponseEntity.ok(service.actualizar(id, datos));

    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
            service.eliminar(id);
            return ResponseEntity.notFound().build();

    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }
    
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }
    
    
}
