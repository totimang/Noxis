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
        CommandLineRunner cargarDatos(ProductoService productoService, CategoriaService categoriaService) {
            return args -> {
                if (categoriaService.listarTodas().isEmpty()) {
                    Categoria memorias = categoriaService.guardar(new Categoria("Memorias", "Memorias RAM"));
                    Categoria almacenamiento = categoriaService.guardar(new Categoria("Almacenamiento", "Discos rigidos y solidos"));
                    Categoria procesadores = categoriaService.guardar(new Categoria("Procesadores", "Procesadores Intel y AMD"));
                                
                    productoService.guardar(new Producto("16GB DDR5", 378693.41, 20, memorias));
                    productoService.guardar(new Producto("HD HDD 1TB", 14600, 10, almacenamiento));
                    productoService.guardar(new Producto("AMD RYZEN 7 9800X3D", 771916.97, 10, procesadores));
                    productoService.guardar(new Producto("32GB DDR5", 700030.97, 10, memorias));
                    productoService.guardar(new Producto("Intel i7 8700k", 871916.97, 10, procesadores));
                }                
            };
        }

}

/* 
Para reiniciar los valores de mi tabla en phpmyadmins,
pararse en la tabla 'ecommerse' y escribir en SQL 
la primer linea y despues la segunda:

DROP TABLE producto;
DROP TABLE categoria;
*/