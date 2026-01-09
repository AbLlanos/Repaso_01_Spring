package repaso01Spring.example.Spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import repaso01Spring.example.Spring.entity.Cliente;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String irLogin(){

        return "Paginas/loginScreen";
    }

    //Llamar a Authentication
    @GetMapping("/postLogin")
    public String redirigirPorRol(Authentication authentication){

        //Lllamar a la clase User( si leugo en role no sale . getAuthorites esta mal) y luego (la misma), porsteriormente poner authentacion.getPrincipal() con ide

        User user = (User) authentication.getPrincipal();

        //Usar .getAutorities.stream
        //Llamar .map y terminar con ide el granted y luego .getA
        //.findFirst()
        //.orElse("")
        String role = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority() )
                .findFirst()
                .orElse("");

        //Crear un if con los roles y donde redirige con return y redirect
        //Usar "ROLE_ADMIN".equalas(role)
        if ("ROLE_ADMIN".equals(role)){
            return "redirect:/Admin/adminPerfil";
        } else if ("ROLE_USUARIO".equals(role) ) {
            return "redirect:/Cliente/DirigirseclienteScreen";
        }

        return "redirect:/login?error";

    }


}
