package cl.bibliosmart.bibliosmart.modules.bibliotecario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.repository.PrestamoRepository;

@Service
public class PrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;

    //Registrar nuevo prestamo

    //Registrar devolucion de un librp

    //Registrar pagos de multa asociado al rut de un lector

    //Cambiar estado del ejemplar de un libro

    //Editar informacion de libros/ejemplares
}
