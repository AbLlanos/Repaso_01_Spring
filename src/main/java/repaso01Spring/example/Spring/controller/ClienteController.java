package repaso01Spring.example.Spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repaso01Spring.example.Spring.entity.Cliente;
import repaso01Spring.example.Spring.services.ClienteService;

import java.time.LocalDateTime;

@Controller
//Usar request mapping pero cambiar siempre le return ya que esto solo
//hace que la ruta en el navegador siempre salgo con el sufijo /Cliente
//como consecuncia se busca /Cliente/DirigirseClienteScreen en el navegador
@RequestMapping("/Cliente")

public class ClienteController {

    //Poner ruta completa en return siempre
    @GetMapping("/DirigirseclienteScreen")
    public String irPantallaCliente(){
        return "Paginas/clienteScreen";

    }

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
        clienteService.guardarCliente(cliente);
        model.addAttribute("mensaje", "Cliente guardado correctamente");
        return "Paginas/registroCliente";
    }

}
