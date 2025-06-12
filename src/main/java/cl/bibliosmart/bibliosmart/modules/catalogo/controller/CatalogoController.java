package cl.bibliosmart.bibliosmart.modules.catalogo.controller;

import cl.bibliosmart.bibliosmart.modules.catalogo.model.Libro;
import cl.bibliosmart.bibliosmart.modules.catalogo.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogoController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "pagina", required = false, defaultValue = "1") int pagina,
            Model model) {

        Page<Libro> pageLibros = libroService.listarLibros(q, categoria, pagina);

        model.addAttribute("libros", pageLibros.getContent());
        model.addAttribute("pagina", pagina);
        model.addAttribute("totalPaginas", pageLibros.getTotalPages());
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("categoria", categoria == null ? "" : categoria);

        return "catalogo";
    }

}
