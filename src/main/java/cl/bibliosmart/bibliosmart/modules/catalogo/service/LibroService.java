package cl.bibliosmart.bibliosmart.modules.catalogo.service;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Ejemplar;
import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;
import cl.bibliosmart.bibliosmart.modules.catalogo.repository.EjemplarRepository;
import cl.bibliosmart.bibliosmart.modules.catalogo.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EjemplarRepository ejemplarRepository;

    private static final int PAGE_SIZE = 40;

    @SuppressWarnings("null")
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
    @SuppressWarnings("deprecation")
    public Libro buscarPorId(Long id){
        return libroRepository.getById(id);
    }
    public int contarEjemplarPorLibroId(Long libroId){
        return ejemplarRepository.countByLibroId(libroId);
    }
    public List<Ejemplar> listarEjemplaresPorLibro(Long libroId){
        return ejemplarRepository.findByLibroId(libroId);
    }
    public Optional<Libro> buscarPorIsbn(String isbn){
        return libroRepository.findByIsbn(isbn);
    }
    public void actualizarLibro(Libro libro){
        libroRepository.save(libro);
    }
}
