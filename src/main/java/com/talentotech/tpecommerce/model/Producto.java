package com.talentotech.tpecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "producto")
@Getter // Genera automáticamente todos los getters
@Setter // Genera automáticamente todos los setters
@NoArgsConstructor // Genera el constructor vacío obligatorio para JPA/Spring
@AllArgsConstructor // Genera un constructor con todos los campos (id, nombre, precio, stock, categoria)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "El nombre del producto no puede estar vacío!")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Positive(message = "El precio debe ser mayor que cero!")
    @Column(name = "precio", nullable = false)
    private double precio;
    
    @PositiveOrZero(message = "El stock no puede ser negativo!")
    @Column(name = "stock", nullable = false)
    private int stock;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Constructor sin id personalizado (el id lo asigna la base de datos)
    public Producto(String nombre, double precio, int stock, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Mantenemos el toString personalizado por seguridad en relaciones JPA 
    // y para conservar tu formato con el "Sin categoría"
    @Override
    public String toString() {
        String nombreCat = (categoria != null) ? categoria.getNombre() : "Sin categoría";
        return "ID: " + id +
                " | " + nombre +
                " | $" + precio +
                " | Stock: " + stock +
                " | Categoría: " + nombreCat;
    }
}
