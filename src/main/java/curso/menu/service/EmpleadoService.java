package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Empleado;
import curso.menu.repository.EmpleadoRepository;

@Service
public class EmpleadoService{

	@Autowired
	private EmpleadoRepository miEmpleado;
	
	public Empleado guardarEmpleado(Empleado model) {
		Empleado result = new Empleado();
		
		try {
			result = miEmpleado.save(model);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
//	public Empleado guardarEmpleadoId(Empleado model, int id) {
//		Empleado result = getOneEmpleado(id);
//		
//		result.setNombreEmpleado(model.getNombreEmpleado());
//		
//		try {
//			model = miEmpleado.saveAndFlush(result);
//		}catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		
//		return model;
//	}
	
	public List<Empleado> guardarAllEmpleados(List<Empleado> empleados) {
		List<Empleado> result = new ArrayList<Empleado>();
		
		try {
			result = miEmpleado.saveAll(empleados);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	
	public Empleado getOneEmpleado(Integer id) {
		Empleado retrieved = new Empleado();
        try {
            Optional<Empleado> empleado = miEmpleado.findById(id);
            retrieved = empleado.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe empleado con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne empleado: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Empleado> getAllEmpleado() {
		List<Empleado> result = new ArrayList<Empleado>();
		try {
			result = miEmpleado.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public Empleado getByUsername(String username) {
		Empleado empleado = new Empleado();
        try {
        	empleado = miEmpleado.findByUsername(username);
        }catch (NoSuchElementException exc) {
            System.out.println("No existe empleado con id " + username);
        }catch (Exception ex) {
            System.out.println("Error en getOne empleado: " + ex.getMessage());
        }
		return empleado;
	}
	
	
	public void deleteByIdEmpleado (Integer id) {
		try {
			miEmpleado.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	

}
