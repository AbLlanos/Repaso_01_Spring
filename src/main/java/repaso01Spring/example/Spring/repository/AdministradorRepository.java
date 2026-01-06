package repaso01Spring.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import repaso01Spring.example.Spring.entity.Administrador;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    //Poner el nombre de la bariable despues del by
    Optional<Administrador> findBynombreAdmin(String nombreAdmin);

}
