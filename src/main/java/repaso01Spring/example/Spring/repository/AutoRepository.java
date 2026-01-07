package repaso01Spring.example.Spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repaso01Spring.example.Spring.entity.Autos;

import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Autos, Long> {

    Optional<Autos> findBynombreAuto (String nombreAuto);

}
