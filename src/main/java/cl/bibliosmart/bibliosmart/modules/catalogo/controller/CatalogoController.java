package cl.bibliosmart.bibliosmart.modules.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;
import cl.bibliosmart.bibliosmart.modules.catalogo.service.LibroService;

@Controller
public class CatalogoController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/catalogo")
    public String mostrarCatalogo(
            @RequestParam(value = "pagina", defaultValue = "1") int pagina,
            @RequestParam(value = "categoria", required = false) String categoria,
            Model model) {

        Page<Libro> librosPage = libroService.obtenerLibros(pagina, categoria);

        model.addAttribute("libros", librosPage.getContent());
        model.addAttribute("pagina", pagina);
        model.addAttribute("totalPaginas", librosPage.getTotalPages());
        model.addAttribute("categoria", categoria != null ? categoria : "");

        return "catalogo";  // nombre de la plantilla HTML (catalogo.html)
    }
}
