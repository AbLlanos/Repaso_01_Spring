package repaso01Spring.example.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/irAdmin")
    public String irAdmin(){

        //Usar redirect para mostrar listas
        return "redirect:/Admin/adminPerfil";
    }

    @GetMapping("/login")
    public String irLogin(){

        return "Paginas/loginScreen";
    }

}
