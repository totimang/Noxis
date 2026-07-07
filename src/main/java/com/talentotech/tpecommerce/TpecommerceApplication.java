package com.talentotech.tpecommerce;

import com.talentotech.tpecommerce.model.Categoria;
import com.talentotech.tpecommerce.model.Producto;
import com.talentotech.tpecommerce.service.CategoriaService;
import com.talentotech.tpecommerce.service.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication/*(scanBasePackages = {
    "com.talentotech.tpecomerce",
    "com.techlab.ecommerce"
})*/
public class TpecommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpecommerceApplication.class, args);
	}
        
        @Bean
        CommandLineRunner cargarDatos(CategoriaService categoriaService, ProductoService productoService) {
            return args -> {
                if (categoriaService.listarTodas().isEmpty()) {
                    Categoria memorias = categoriaService.guardar(new Categoria(null, "Memorias", "Memorias RAM"));
                    Categoria almacenamiento = categoriaService.guardar(new Categoria(null, "Almacenamiento", "Discos rigidos y solidos"));
                    Categoria procesadores = categoriaService.guardar(new Categoria(null, "Procesadores", "Procesadores Intel y AMD"));
                    
                    productoService.guardar(new Producto(null,"16GB DDR5", 599.999, 20,
                    "https://mexx-img-2019.s3.amazonaws.com/Memoria-Ram-DDR5-16Gb-5600-Mhz-Kingston-Fury-Beast-Rgb_48227_2.jpeg",
                    memorias));
                    
                    productoService.guardar(new Producto(null,"HD HDD 1TB", 179289.85, 20,
                    "https://fullh4rd.com.ar/img/productos/12/hd-hdd-1tb-seagate-skyhawk-sata-iii-35-0.jpg",
                    almacenamiento));
                    
                    productoService.guardar(new Producto(null,"AMD RYZEN 7 9800X3D", 835979.94, 10,
                    "https://fullh4rd.com.ar/img/productos/1/micro-amd-ryzen-7-9800x3d-cvideo-scooler-am5-0.jpg",
                    procesadores));
                    
                    productoService.guardar(new Producto(null,"32GB DDR5", 1067279, 10,
                    "https://try.com.ar/wp-content/uploads/2026/03/memoria-kingston-ddr5-32gb-6000mhz-fury-beast-rgb-1024x1024.webp",
                    memorias));

                }                
            };
        }
}

/* 
Para crear la base de datos en phpmyadmins,
pararse  en SQL:

CREATE DATABASE ecommerce
    DEFAULT CHARACTER SET = 'utf8mb4';
*/


/* 
Para reiniciar los valores de mi tabla en phpmyadmins,
pararse en la tabla 'ecommerse' y escribir en SQL 
la primer linea y despues la segunda:

DROP TABLE producto;
DROP TABLE categoria;
*/