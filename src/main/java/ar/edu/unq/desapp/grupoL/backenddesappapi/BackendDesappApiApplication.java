package ar.edu.unq.desapp.grupoL.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Configuration
public class BackendDesappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}


//TODO
// validación user para no crear user's con wallet repetida (DONE)
// Poder eliminar un usuario (para eso eliminar sus transacciones) (DONE)
// Desacoplar lógica de Crypto de User
// Persistir en Hibernate
// Consola H2
// Swagger
// Heroku
