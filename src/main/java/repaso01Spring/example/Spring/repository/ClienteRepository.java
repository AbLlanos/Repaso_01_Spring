package repaso01Spring.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repaso01Spring.example.Spring.entity.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long > {

    //Datos
    List<Cliente> findByCedula(String cedula) ;
    //Lista
    List<Cliente> findByRol (String rol);

    //
    Optional<Cliente> findById (Long id);

    // 5. Funcion para buscar correo en el autenticaion

    Optional<Cliente> findByCorreoElectronico(String correo);

}
