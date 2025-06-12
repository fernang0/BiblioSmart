package cl.bibliosmart.bibliosmart.modules.catalogo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Busca libros activos (activo = true) y que la categoría contenga el filtro, con paginación
    Page<Libro> findByActivoTrueAndCategoriaContainingIgnoreCase(String categoria, Pageable pageable);

    // Busca todos activos (para cuando no hay filtro)
    Page<Libro> findByActivoTrue(Pageable pageable);
}
