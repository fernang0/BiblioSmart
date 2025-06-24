package cl.bibliosmart.bibliosmart.modules.bibliotecario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
    Prestamo findByEjemplarIdAndUsuarioId(Long ejemplarId, Long usuarioId);
    
}
