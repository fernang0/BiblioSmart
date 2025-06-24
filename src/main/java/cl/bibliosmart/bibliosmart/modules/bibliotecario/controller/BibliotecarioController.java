package cl.bibliosmart.bibliosmart.modules.bibliotecario.controller;

import cl.bibliosmart.bibliosmart.modules.bibliotecario.service.PrestamoService;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.PoliticaPrestamo;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.model.Prestamo;
import cl.bibliosmart.bibliosmart.modules.bibliotecario.service.PoliticaPrestamoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bibliotecario")
public class BibliotecarioController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private PoliticaPrestamoService politicaPrestamoService;

    @GetMapping("/panel")
    public String panelBibliotecario() {
        return "bibliotecario/panel";
    }

    @GetMapping("/nuevo-prestamo")
    public String nuevoPrestamoForm(Model model) {
        List<PoliticaPrestamo> politicas = politicaPrestamoService.obtenerTodas();
        model.addAttribute("politicas", politicas);
        return "bibliotecario/nuevo-prestamo";
    }

    @PostMapping("/prestamos/guardar")
    public String registrarPrestamo(
            @RequestParam("rutUsuario") String rutUsuario,
            @RequestParam("ejemplarId") String ejemplarId,
            @RequestParam("politicaPrestamoId") Long politicaPrestamoId,
            Model model
    ) {
        try {
            prestamoService.registrarPrestamo(rutUsuario, ejemplarId, politicaPrestamoId);
            model.addAttribute("msgExito", "Préstamo registrado correctamente.");
        } catch (Exception e) {
            model.addAttribute("msgError", "Error al registrar el préstamo: " + e.getMessage());
        }

        List<PoliticaPrestamo> politicas = politicaPrestamoService.obtenerTodas();
        model.addAttribute("politicas", politicas);

        return "bibliotecario/nuevo-prestamo";
    }

    @GetMapping("/devoluciones")
    public String devolucionesBibliotecario() {
        return "bibliotecario/devoluciones";
    }

    @PostMapping("/devoluciones/registrar")
    public String registrarDevolucion(
        @RequestParam("ejemplarId") String ejemplarId,
        @RequestParam("rutUsuario") String rutUsuario,
        Model model
    ) {
        try {
            Prestamo prestamo = prestamoService.registrarDevolucion(ejemplarId, rutUsuario);
            model.addAttribute("prestamo", prestamo);
            model.addAttribute("msgExito", "Devolución registrada correctamente.");
        } catch (Exception e) {
            model.addAttribute("msgError", "Error: " + e.getMessage());
        }

        return "bibliotecario/devoluciones";
    }
}
