package cl.bibliosmart.bibliosmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bibliosmart.bibliosmart.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String correo);
    
}
