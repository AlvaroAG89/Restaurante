package curso.menu;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	//Manejo de error 403. Invocamos al HTML 403. Debe implementarse WebMvcConfigurer
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error403").setViewName("error_403");
	}

}
