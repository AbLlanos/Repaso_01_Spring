package repaso01Spring.example.Spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repaso01Spring.example.Spring.entity.Autos;
import repaso01Spring.example.Spring.repository.AutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    //1 Guardar


    public Autos guardarAuto( Autos autos) {
        return autoRepository.save( autos);
    }

    //2 Traer todo


    public List<Autos> getAutoRepository() {
        return autoRepository.findAll();
    }

    //3 Eliminar auto


    public void eliminarAuto(Long id) {
        this.autoRepository.deleteById(id);
    }

    //4 Buscar


    public Optional<Autos> setAutoRepository(String autos) {
        return autoRepository.findBynombreAuto(autos);
    }
}
