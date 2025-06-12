package cl.bibliosmart.bibliosmart.modules.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bibliosmart.bibliosmart.modules.login.model.Usuario;
import cl.bibliosmart.bibliosmart.modules.login.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}