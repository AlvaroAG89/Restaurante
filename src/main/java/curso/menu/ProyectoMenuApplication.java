package curso.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoMenuApplication implements CommandLineRunner{

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoMenuApplication.class, args);
	}

	//Genera 2 contraseñas cifradas de la misma de origen. Al mostrarlas en consola, las copio y pego en la
	//BD para que al logearse con la no cifrada, la cifre y la compare con la almacenada
	@Override
	public void run(String... args) throws Exception {
		String pasword = "12345";
		
		//genero contraseña encriptada
		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder.encode(pasword);
			System.out.println(bcryptPassword);
		}
		
	}

}
