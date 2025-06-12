package cl.bibliosmart.bibliosmart.modules.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bibliosmart.bibliosmart.modules.login.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String correo);
    
}
