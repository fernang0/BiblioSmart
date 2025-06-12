package cl.bibliosmart.bibliosmart.modules.catalogo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;
import cl.bibliosmart.bibliosmart.modules.catalogo.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    private static final int PAGE_SIZE = 40;

    public Page<Libro> obtenerLibros(int pagina, String categoria) {
        PageRequest pageRequest = PageRequest.of(pagina - 1, PAGE_SIZE);
        if (categoria == null || categoria.isBlank()) {
            return libroRepository.findByActivoTrue(pageRequest);
        } else {
            return libroRepository.findByActivoTrueAndCategoriaContainingIgnoreCase(categoria.trim(), pageRequest);
        }
    }
}
