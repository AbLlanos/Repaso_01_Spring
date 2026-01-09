package repaso01Spring.example.Spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repaso01Spring.example.Spring.entity.Notas;
import repaso01Spring.example.Spring.repository.NotasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotasService {

    @Autowired
    private NotasRepository notasRepository;

    //1 Guardar

    public Notas guardarNotas(Notas notas) {
        return notasRepository.save(notas);
    }

    //2 Listar todo


    public List<Notas> mostraTodasNotas() {
        return notasRepository.findAll();
    }

    //3 Buscar por ID
    public Optional<Notas> buscarNotaPorID(Long idNota) {
        return notasRepository.findById(idNota);
    }
    // 4 Eliminar


    public void eliminarNota(Long id) {
        notasRepository.deleteById(id);
    }

    //5 Buscar por id del cliente la lista de registro

    public List<Notas> buscarNotasPorCliente(Long clienteId) {
        return notasRepository.findByIdNota(clienteId);
    }

}
