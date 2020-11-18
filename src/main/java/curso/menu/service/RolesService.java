package curso.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Empleado;
import curso.menu.model.Role;
import curso.menu.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolRepo;
	
	
	public void saveRole (Role role, Empleado empleado) {
		if (empleado.getIdEmpleado()==null) {
			
			
			
		} else {
		int id = empleado.getIdEmpleado();
		List<Role> roles = new ArrayList<Role> ();
		roles = getAllRoles();
		
		for (Role rol : roles) {

			if (rol.getEmpleado().getIdEmpleado() == id) {
				eliminateRole(rol);
			}
		}
		
		role.setEmpleado(empleado);
		rolRepo.save(role);
	}}
	
	public List<Role> getAllRoles() {
		List<Role> result = new ArrayList<Role>();
		try {
			result = rolRepo.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public void eliminateRole (Role rol) {
		
		int id = rol.getId();
		
			try {
				rolRepo.deleteById(id);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	
}
