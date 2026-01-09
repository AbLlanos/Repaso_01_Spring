package repaso01Spring.example.Spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

//Lllamar configuration y EnableWebSecurity

public class ConfiguracionSeguridad {

    //Poenr public SecurityFilter - security( buscarr Http Security se crea htttp luego throws)

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            //Completar Exception con ide
            Exception {
            //Poner http.authorize y completar con ide
            // Crear auth y llamar auth.requestMatcher
            http.authorizeHttpRequests( auth -> auth
                    //Despues de llamar a auth -> usar .requestMatcher,usar comas para aumentar y al final poner
                    // .permitAll para que todo puede verlo
                    .requestMatchers("/","/login","/Cliente/registroCliente","/Cliente/guardarCliente","/postLogin").permitAll()

                    .requestMatchers("/Admin/**").hasRole("ADMIN")

                    .requestMatchers("/Cliente/**").hasRole("USUARIO")


            )//Llamar a .formLogin para establecer una ruta base
                    //Crear variable form -> form
                    //Llamar loginPage(ruta)
                    //.permitAll
                    //.defaultSuccessURL("/postLogin",true)
                    .formLogin( form -> form
                            .loginPage("/login")
                            .permitAll()
                            .defaultSuccessUrl("/postLogin", true)


            )   //Crear variable logout -> logout
                    // llamar con ide logoutUrl
                    // luego .logoutSuccesgul("/login?logout)
                    .logout( logout -> logout

                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .permitAll()
        //Poner ;
                    );

            //Ir login controller

            return http.build();



    }

    //6.0 Llamar esto si no fucncion el encirpatdo
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
