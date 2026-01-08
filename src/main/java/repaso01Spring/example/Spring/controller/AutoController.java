package repaso01Spring.example.Spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repaso01Spring.example.Spring.entity.Autos;
import repaso01Spring.example.Spring.services.AutoService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/Auto")
public class AutoController {

    @Autowired
    private AutoService autoService;

    //1 Ir

    @GetMapping("/registroAuto")
        public String registroAuto(Model model){

        Autos auto = new Autos();
        auto.setFecha_ingreso_auto(LocalDateTime.now());

        model.addAttribute("auto", auto);

        return "Paginas/registroAuto";
    }

    //2 Guardar
    //Usar ModelAttribute
    @PostMapping("/guardarAuto")
    public String guardarAuto(@Valid @ModelAttribute("auto") Autos autos,
                              BindingResult bindingResult,
                              Model model){

        if (bindingResult.hasErrors()){

            model.addAttribute("message","error en registro auto");

            autos.setFecha_ingreso_auto(LocalDateTime.now());
            return "Paginas/registroAuto";

        }

        autos.setFecha_ingreso_auto(LocalDateTime.now());
        autoService.guardarAuto(autos);

        model.addAttribute("message","auto registrado");

        return  "/index";

    }






}
