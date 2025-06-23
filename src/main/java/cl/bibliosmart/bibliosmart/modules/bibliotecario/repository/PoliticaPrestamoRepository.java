package cl.bibliosmart.bibliosmart.modules.bibliotecario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.PoliticaPrestamo;

public interface PoliticaPrestamoRepository extends JpaRepository<PoliticaPrestamo, Long>{
}
