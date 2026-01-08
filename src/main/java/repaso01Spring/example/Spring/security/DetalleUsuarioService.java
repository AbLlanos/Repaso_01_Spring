package repaso01Spring.example.Spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repaso01Spring.example.Spring.entity.Cliente;
import repaso01Spring.example.Spring.repository.ClienteRepository;

//1 Primero poenr service
@Service

//2. Implementar UserDeatilService
public class DetalleUsuarioService implements UserDetailsService
{

    //3.Traer la entidad que posees los roles
    @Autowired
    private ClienteRepository clienteRepository;

    //4. Buscar en el id UserDetails
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.findByCorreoElectronico(email).orElseThrow(()-> new UsernameNotFoundException("Correo no econtrado"));

        return User.builder()
                .username(cliente.getCorreoElectronico())
                .password(cliente.getPassword())
                .roles(cliente.getRol())
                .build();
    }
}
