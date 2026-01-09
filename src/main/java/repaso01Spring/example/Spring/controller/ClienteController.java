package repaso01Spring.example.Spring.controller;

import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repaso01Spring.example.Spring.entity.Cliente;
import repaso01Spring.example.Spring.entity.Notas;
import repaso01Spring.example.Spring.services.ClienteService;
import repaso01Spring.example.Spring.services.NotasService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
//Usar request mapping pero cambiar siempre le return ya que esto solo
//hace que la ruta en el navegador siempre salgo con el sufijo /Cliente
//como consecuncia se busca /Cliente/DirigirseClienteScreen en el navegador
@RequestMapping("/Cliente")

public class ClienteController {



    /* 0V1. Poner ruta completa en return siempre
        Se modifica luego para poder captura los datos del usario

    @GetMapping("/DirigirseclienteScreen")
    public String irPantallaCliente(){
        return "Paginas/clienteScreen";

    }
    */
    @GetMapping("/registroCliente")
    public String irRegistro(Model model){
        //Para poner datos directamente instanciar la clase
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setFechaActivacion(LocalDateTime.now());
        model.addAttribute("cliente", new Cliente());
        return "Paginas/registroCliente";
    }

    //0 Traer el servicio

    @Autowired
    private ClienteService clienteService;

    //1. Guardar
    // Usar postmapping
    //Crear public y poner entidad

    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                 BindingResult bindingResult,
                                 Model model){
        if (bindingResult.hasErrors()) {
            cliente.setFechaActivacion(LocalDateTime.now());

            // vuelve al formulario mostrando los errores
            return "Paginas/registroCliente";
        }

        cliente.setFechaActivacion(LocalDateTime.now());
        //Cifrar contrraseña antes de guardar

        clienteService.guardarCliente(cliente);
        model.addAttribute("mensaje", "Cliente guardado correctamente");
        return "Paginas/registroCliente";
    }


    //Como crear historial de registro
    
    @Autowired
    private NotasService notasService;

    // 10. Capturar datos del usuario logeado

    private Cliente obtenerDatosUsuario(Authentication authentication){
        String email = authentication.getName();
        return clienteService.encontrarClienteCorreo(email).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    // 0V2

    @GetMapping("/DirigirseclienteScreen")
    public String irPantallaCliente(Authentication authentication,
                                    Model model){
        Cliente usuario = obtenerDatosUsuario(authentication);
        model.addAttribute("usuario",usuario);
        return "Paginas/clienteScreen";
    }

    @GetMapping("/crearNota")
    public String crearNota(Authentication authentication,
                            Model model){

        Cliente usuario = obtenerDatosUsuario(authentication);
        Notas nuevaNota = new Notas();


        nuevaNota.setClienteDatos(usuario);
        nuevaNota.setFechaIngresoNota(LocalDateTime.now());

        model.addAttribute("nota", nuevaNota);

        return "Paginas/crearNotaScreen";


    }

    @PostMapping("/guardarNota")
    public String guardarNota(@Valid @ModelAttribute("nota") Notas notas,
                              BindingResult bindingResult,
                              Authentication authentication){

        if (bindingResult.hasErrors()){

            return "Paginas/crearNotaScreen";

        }

        Cliente usuario = obtenerDatosUsuario(authentication);
        if (!notas.getClienteDatos().getId().equals(usuario.getId())) {
            return "redirect:/Cliente/misNotas";  // Seguridad
        }

        notasService.guardarNotas(notas);

        return "redirect:/Cliente/misNotas";

    }

    @GetMapping("/misNotas")
    public String misNotas(Authentication authentication,
                           Model model)
    {

        Cliente usuario = obtenerDatosUsuario(authentication);

        List<Notas> misNotas = notasService.buscarNotasPorCliente(usuario.getId());
        model.addAttribute("notas",misNotas);

        return "Paginas/misNotasScreen";


    }

    


}
