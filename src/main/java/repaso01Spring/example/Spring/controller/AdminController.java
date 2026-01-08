package repaso01Spring.example.Spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repaso01Spring.example.Spring.entity.Administrador;
import repaso01Spring.example.Spring.entity.Cliente;
import repaso01Spring.example.Spring.services.AdministradorService;
import repaso01Spring.example.Spring.services.ClienteService;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    // 3 Listar usuarios
    // 1 usar get mapping
    // 2 PARA POENR FILTRO USAR REQUESTA PARA Y LEUGO REQUIERED DEFAULT VALUE Y DESPUES LA VARAIBLE ASOCIADA

    @Autowired
    private ClienteService clienteService;

    @GetMapping ("/adminPerfil")
    public String listarDocentes(@RequestParam(name = "buscarCliente" , required = false, defaultValue = "" ) String buscarCliente,
        Model model ){


       /* Primero forma
        List<Cliente> clientes = clienteService.buscarClienteporCedula(buscarCliente);
        */

        //Segunda forma
        //No usar return
        List<Cliente> clientes;
        if ( buscarCliente.isEmpty()){

            clientes = clienteService.mostrarClientes();

        }else {

            clientes = clienteService.buscarClienteporCedula(buscarCliente);

        }

        model.addAttribute("buscarCliente", buscarCliente);
        model.addAttribute("clientes", clientes);

        return "Paginas/adminScreen";

    }


    //4 editar
    // Usar path variable

    @GetMapping("/editarCliente/{id}")
    public String editarDatosCliente( @PathVariable Long id, Model model){

        Optional<Cliente> cliente = clienteService.buscarPorId(id);

        model.addAttribute("cliente", cliente);

        return "Paginas/editarClienteScreen";

    }

        //4.1 Funcion para actualziar
    @PostMapping("/actualizarCliente")
    public String actualizarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                    BindingResult bindingResult,
                                    Model model){
        if (bindingResult.hasErrors()) {
            return "Paginas/editarClienteScreen";
        }
        clienteService.guardarCliente(cliente);
        return "redirect:/Admin/adminPerfil";
    }

    //5 Eliminar

    @GetMapping("/eliminarCliente/{id}")

    public String eliminarClientePorId(@PathVariable Long id, Model model){

        clienteService.eliminarCliente(id);

        return "redirect:/Admin/adminPerfil";


    }




}
