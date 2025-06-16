package cl.bibliosmart.bibliosmart.modules.catalogo.service;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;
import cl.bibliosmart.bibliosmart.modules.catalogo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    private static final int PAGE_SIZE = 40;

    public Page<Libro> listarLibros(String q, String categoria, int pagina) {
        PageRequest pageRequest = PageRequest.of(pagina - 1, PAGE_SIZE);

        boolean tieneBusqueda = q != null && !q.trim().isEmpty();
        boolean tieneCategoria = categoria != null && !categoria.trim().isEmpty();

        if (!tieneBusqueda && !tieneCategoria) {
            return libroRepository.findAllActivos(pageRequest);
        } else if (tieneBusqueda && !tieneCategoria) {
            return libroRepository.buscarPorQ(q.trim(), pageRequest);
        } else if (!tieneBusqueda && tieneCategoria) {
            return libroRepository.findByCategoriaActiva(categoria.trim(), pageRequest);
        } else {
            return libroRepository.buscarPorQYCategoria(q.trim(), categoria.trim(), pageRequest);
        }
    }
    public Libro buscarPorId(Long id){
        return libroRepository.getById(id);
    }

}
