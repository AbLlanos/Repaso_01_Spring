package repaso01Spring.example.Spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repaso01Spring.example.Spring.entity.Cliente;
import repaso01Spring.example.Spring.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    //Llamar repositorios

    @Autowired
    private ClienteRepository clienteRepository;

    //1. Guardar
    // 1.Llamar cliente
    // 2. Usar repository
    // 3. Usar .save y guardar entidad

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //2. Realizar busqueda
    //Usar optiona y entidad
    //Poner el mismo tipo de variable
    //Llamar a la funcion desde el repositorio y enviar variable

    public List<Cliente> buscarClienteporCedula(String cedula) {

        if (cedula == null || cedula.trim().isEmpty() ){

            return clienteRepository.findAll();

        } else {

            return clienteRepository.findByCedula(cedula.trim());

        }


    }

    //3. Eliminar
    // Es un void y enviar el tipo de id
    // Llamar deletebyid directamente
    // Eliminar return por que es void


    public void eliminarCliente (Long id) {
        clienteRepository.deleteById(id);
    }

    //4 Obtener todos los productos
    // Usa list la entidad y sin variable
    // usar find all


    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }

    //4 Editar

    public Optional<Cliente> buscarPorId(Long id){

        return clienteRepository.findById(id);

    }

    // 5. Funcion para buscar correo en el autenticaion


    public Optional<Cliente> encontrarClienteCorreo(String correo) {
        return clienteRepository.findByCorreoElectronico(correo);
    }
}
