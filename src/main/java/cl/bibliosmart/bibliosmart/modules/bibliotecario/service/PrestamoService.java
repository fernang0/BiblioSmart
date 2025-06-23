package cl.bibliosmart.bibliosmart.modules.bibliotecario.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.PoliticaPrestamo;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.Prestamo;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.repository.PoliticaPrestamoRepository;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.repository.PrestamoRepository;
import cl.bibliosmart.bibliosmart.modules.catalogo.model.Ejemplar;
import cl.bibliosmart.bibliosmart.modules.catalogo.model.EstadoEjemplar;
import cl.bibliosmart.bibliosmart.modules.catalogo.repository.EjemplarRepository;
import cl.bibliosmart.bibliosmart.modules.login.model.Usuario;
import cl.bibliosmart.bibliosmart.modules.login.repository.UsuarioRepository;

@Service
public class PrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private EjemplarRepository ejemplarRepository;

    @Autowired
    private PoliticaPrestamoRepository politicaPrestamoRepository;

    //Registrar nuevo prestamo
        public void registrarPrestamo(String rutUsuario, String codigoEjemplar, Long politicaId) throws Exception {

        // Buscar usuario por RUT
        Usuario usuario = usuarioRepository.findByRut(rutUsuario)
                .orElseThrow(() -> new Exception("Usuario no encontrado con RUT: " + rutUsuario));

        // Buscar ejemplar
        Ejemplar ejemplar = ejemplarRepository.findByCodigoEjemplar(codigoEjemplar)
                .orElseThrow(() -> new Exception("Ejemplar no encontrado con ID: " + codigoEjemplar));

        if (!ejemplar.isDisponible()) {
            throw new Exception("El ejemplar no está disponible para préstamo.");
        }

        // Buscar política de préstamo
        PoliticaPrestamo politica = politicaPrestamoRepository.findById(politicaId)
                .orElseThrow(() -> new Exception("Política de préstamo no encontrada."));

        // Calcular fechas
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaLimite = fechaPrestamo.plusDays(politica.getDiasMaximos());

        // Crear préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setPoliticaPrestamo(politica);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaLimite(fechaLimite);

        // Marcar ejemplar como no disponible
        ejemplar.setEstado(EstadoEjemplar.PRESTADO);;
        ejemplarRepository.save(ejemplar);

        // Guardar préstamo
        prestamoRepository.save(prestamo);
    }

    //Registrar devolucion de un libro

    //Registrar pagos de multa asociado al rut de un lector

    //Cambiar estado del ejemplar de un libro

    //Editar informacion de libros/ejemplares
}
