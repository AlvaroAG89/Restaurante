package curso.menu;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import curso.menu.service.JpaUserDetailsService;



@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	//encriptar contraseña
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Se añaden en esta primera las direcciones que no necesitan login
		http.authorizeRequests().antMatchers("/css/**", "/uploads/**", 
				"/index/**", "/Plato/**").permitAll()
		.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
			.permitAll()
		.and()
		.logout()
			.permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/error403");
		
		
	}

	
	@Autowired   
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	


}
