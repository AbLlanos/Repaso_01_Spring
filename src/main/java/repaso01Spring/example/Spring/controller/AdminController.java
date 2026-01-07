package repaso01Spring.example.Spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repaso01Spring.example.Spring.entity.Administrador;
import repaso01Spring.example.Spring.services.AdministradorService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdministradorService administradorService;

    //Llamar Model model
    //Intanciar entidad
    //Poner model.addAtribute
    //Nunca poner /En el primer return
    @GetMapping("/irRegistroAdmin")
    public String irRegsitroAdmin(Model model){

        Administrador admin = new Administrador();

        admin.setFecha_registro_admin(LocalDateTime.now());
        admin.setAlturaAdmin(180);
        model.addAttribute("admin", new Administrador());

        return "Paginas/registroAdmin";

    }

    //2 Guardar
    //Usar postMapping
    //Crear string y poner @Valid @ModelAtrribute("clase") llamar a clase
    //Para mostrar alertas llamar BindingResul
    //Llamar Model model
    @PostMapping ("/guardarAdmin")
    public String guardarAdmin (@Valid @ModelAttribute("admin") Administrador administrador,
                                BindingResult bindingresult,
                                Model model){

        //Llamar instancia y poner.hasErrors
        if (bindingresult.hasErrors()){
            //Se llama el valor de arriba
            administrador.setFecha_registro_admin(LocalDateTime.now());
            administrador.setAlturaAdmin(180);
            return "Paginas/registroAdmin";
        }

        administrador.setFecha_registro_admin(LocalDateTime.now());
        administrador.setAlturaAdmin(180);
        //Guardar administrador
        administradorService.guardarAdministrador(administrador);

        //Crear mensaje de funcionalidad
        model.addAttribute("message","Admin registrado correctamente");

        return "Paginas/registroAdmin";

    }


}
