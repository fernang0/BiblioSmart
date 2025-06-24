package cl.bibliosmart.bibliosmart.modules.catalogo.repository;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Búsqueda con filtro por título, autores o ISBN, usando LIKE, con paginación
    @Query("SELECT l FROM Libro l WHERE " +
           "(LOWER(l.titulo) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(l.autores) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(l.isbn) LIKE LOWER(CONCAT('%', :q, '%'))) " +
           "AND l.activo = true")
    Page<Libro> buscarPorQ(@Param("q") String q, Pageable pageable);

    // Para el caso sin filtro (q = null o vacío)
    @Query("SELECT l FROM Libro l WHERE l.activo = true")
    Page<Libro> findAllActivos(Pageable pageable);

    // Búsqueda solo por categoría
    @Query("SELECT l FROM Libro l WHERE l.activo = true AND LOWER(l.categoria) = LOWER(:categoria)")
    Page<Libro> findByCategoriaActiva(@Param("categoria") String categoria, Pageable pageable);

    // Búsqueda por texto (q) y categoría
    @Query("SELECT l FROM Libro l WHERE " +
           "(LOWER(l.titulo) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(l.autores) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(l.isbn) LIKE LOWER(CONCAT('%', :q, '%'))) " +
           "AND LOWER(l.categoria) = LOWER(:categoria) " +
           "AND l.activo = true")
    Page<Libro> buscarPorQYCategoria(@Param("q") String q, @Param("categoria") String categoria, Pageable pageable);
    Optional<Libro> findByIsbn(String isbn);
}
