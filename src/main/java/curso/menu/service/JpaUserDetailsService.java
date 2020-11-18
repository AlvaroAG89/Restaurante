package curso.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import curso.menu.model.Empleado;
import curso.menu.model.Role;
import curso.menu.repository.EmpleadoRepository;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private EmpleadoRepository empRepo;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	//clase que se implementa de la interfaz UserDetailsService
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = empRepo.findByUsername(username);
		
		if(empleado == null) {
			logger.error("Error login: no existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("Username " + username + " no existe en el sistema! ");
		}
		
		//cargamos la lista de roles del usuario empleado
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		Role role = empleado.getRol();
			//registramos los roles en springSecurity
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		
		
		if(authorities.isEmpty()) {
			logger.error("Error login: empleado '" + username + "' no tiene roles asignados!");
			throw new UsernameNotFoundException("Username " + username + " no tiene roles asignados! ");
		}
		
		return new User(empleado.getUsername(), empleado.getPassword(), true, true, true, true, authorities);
	}

}
