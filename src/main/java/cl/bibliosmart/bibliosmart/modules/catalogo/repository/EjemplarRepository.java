package cl.bibliosmart.bibliosmart.modules.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Ejemplar;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{
    int countByLibroId(Long libroId);
    List<Ejemplar> findByLibroId(Long libroId);
}
