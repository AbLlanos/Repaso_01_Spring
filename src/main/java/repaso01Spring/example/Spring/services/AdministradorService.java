package repaso01Spring.example.Spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repaso01Spring.example.Spring.entity.Administrador;
import repaso01Spring.example.Spring.repository.AdministradorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    //1 Mostrartodo

    public List<Administrador> mostrarAdministradores() {
        return administradorRepository.findAll();
    }

    //2 Guardar


    public Administrador guardarAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    //3 Buscar


    public Optional<Administrador> buscarAdministradorCedula(String nombreAdmin) {
        return administradorRepository.findBynombreAdmin(nombreAdmin);
    }

    //4 Eliminar


    public void eliminarAdministrador(Long id) {
        this.administradorRepository.deleteById(id);
    }

}
