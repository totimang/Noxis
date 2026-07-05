package com.talentotech.tpecommerce.service;

import com.talentotech.tpecommerce.exception.CarritoNoEncontradoException;
import com.talentotech.tpecommerce.exception.StockInsuficienteException;
import com.talentotech.tpecommerce.model.Carrito;
import com.talentotech.tpecommerce.model.CarritoProducto;
import com.talentotech.tpecommerce.model.Producto;
import com.talentotech.tpecommerce.repository.CarritoProductoRepository;
import com.talentotech.tpecommerce.repository.CarritoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {
    
    private final CarritoRepository carritoRepository;
    private final ProductoService productoService;
    private final CarritoProductoRepository carritoProductoRepository;

    public CarritoService(CarritoRepository carritoRepository,
                          CarritoProductoRepository carritoProductoRepository, ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;
        // el error!!!
        //this.carritoProductoRepository = null;
        this.carritoProductoRepository = carritoProductoRepository;                 
    }

    public Carrito crear() {
        return carritoRepository.save(new Carrito());
    }

    // obtenerPorId centraliza la validación de existencia.
    // Todos los métodos que necesitan un carrito lo llaman primero.
    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoNoEncontradoException(
                        "No se encontró un carrito con id " + id));
    }

    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    public Carrito agregarProducto(Integer carritoId, Integer productoId) {
        Carrito carrito = obtenerPorId(carritoId);
        Producto producto = productoService.obtenerPorId(productoId);

        if (producto.getStock() <= 0) {
            throw new StockInsuficienteException(
                    "El producto \"" + producto.getNombre() + "\" no tiene stock disponible.");
        }

        // buscamos si ya existe una fila con este producto en el carrito
        Optional<CarritoProducto> existente = carritoProductoRepository.findByCarritoAndProducto(carrito, producto);

        if(existente.isPresent()){
            // El producto ya esta en el carrito incrementamos 
            CarritoProducto cp = existente.get();
            cp.setCantidad(cp.getCantidad() + 1);
            carritoProductoRepository.save(cp);

        }else{
            // el producto no esta en el carrito - creamos una fila nueva
            CarritoProducto nuevo = new CarritoProducto(null,carrito,producto,1);
            carritoProductoRepository.save(nuevo);
        }

        // Descuenta una unidad de stock y persiste el cambio
        producto.setStock(producto.getStock() - 1);
        productoService.guardar(producto);

        return carritoRepository.save(carrito);
    }

    // clear() quita los productos de la lista en memoria.
    // save() persiste ese cambio eliminando las filas de la tabla intermedia.
    public Carrito vaciar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carrito.getProductos().clear();
        return carritoRepository.save(carrito);
    }

    public void eliminar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carritoRepository.delete(carrito);
    }
    
}
