package cl.bibliosmart.bibliosmart.modules.bibliotecario.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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

    public Prestamo registrarDevolucion(String ejemplarId, String rutUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByRut(rutUsuario);
        Optional<Ejemplar> ejemplarOpt = ejemplarRepository.findByCodigoEjemplar(ejemplarId);

        if (usuarioOpt.isEmpty() || ejemplarOpt.isEmpty()) {
            throw new RuntimeException("Usuario o ejemplar no encontrados.");
        }

        Usuario usuario = usuarioOpt.get();
        Ejemplar ejemplar = ejemplarOpt.get();

        Prestamo prestamo = prestamoRepository.findByEjemplarIdAndUsuarioId(ejemplar.getId(), usuario.getId());

        if (prestamo == null) {
            throw new RuntimeException("No se encontró un préstamo activo para este ejemplar y usuario.");
        }

        LocalDate hoy = LocalDate.now();
        prestamo.setFechaDevolucion(hoy);

        // Calcular atraso
        LocalDate fechaLimite = prestamo.getFechaLimite();
        int diasAtraso = 0;
        int multa = 0;

        if (hoy.isAfter(fechaLimite)) {
            diasAtraso = (int) ChronoUnit.DAYS.between(fechaLimite, hoy);
            multa = diasAtraso * 1000; // por ejemplo: 1000 pesos por día de atraso

            // Actualizar deuda del usuario
            int deudaActual = usuario.getDeuda() != null ? usuario.getDeuda() : 0;
            usuario.setDeuda(deudaActual + multa);
            usuarioRepository.save(usuario);
        }

        prestamo.setDiasAtraso(diasAtraso);
        prestamo.setMulta(multa);

        return prestamoRepository.save(prestamo);
    }




    //Registrar pagos de multa asociado al rut de un lector

    //Cambiar estado del ejemplar de un libro

    //Editar informacion de libros/ejemplares
}
