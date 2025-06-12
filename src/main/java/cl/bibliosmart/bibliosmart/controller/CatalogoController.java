package cl.bibliosmart.bibliosmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        // Aquí puedes agregar datos que necesite la vista
        // model.addAttribute("listaProductos", lista);

        return "catalogo"; // nombre del archivo catalogo.html en templates
    }
}
