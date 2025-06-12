package cl.bibliosmart.bibliosmart.modules.catalogo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        return "catalogo"; // nombre del archivo catalogo.html en templates
    }
}
