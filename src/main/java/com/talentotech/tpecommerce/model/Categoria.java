package com.talentotech.tpecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "categoria")
@Getter // Genera automáticamente todos los getters
@Setter // Genera automáticamente todos los setters
@NoArgsConstructor // Genera el constructor vacío obligatorio para JPA
@AllArgsConstructor // Genera un constructor con todos los campos (id, nombre, descripcion)
@ToString // Genera un método toString() estándar
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "El nombre de la categoría no puede estar vacía!")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "descripcion", length = 50)
    private String descripcion;

    // Constructor personalizado (sin ID) ya que el ID lo maneja la base de datos
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}