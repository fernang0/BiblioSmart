package cl.bibliosmart.bibliosmart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // nombre del archivo login.html (en templates)
        }

}
