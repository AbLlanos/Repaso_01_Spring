package repaso01Spring.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import repaso01Spring.example.Spring.entity.Notas;

import java.util.List;
import java.util.Optional;

public interface NotasRepository extends JpaRepository<Notas, Long> {

    // Lista de solo las notas creadas por el usuario
    List<Notas> findByIdNota(Long idNota);

}
