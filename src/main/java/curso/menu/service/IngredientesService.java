package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Ingredientes;
import curso.menu.repository.IngredientesRepository;

@Service
public class IngredientesService {

	@Autowired
	private IngredientesRepository miIngrediente;
	
	public Ingredientes guardarIngredientes(Ingredientes model) {
		Ingredientes result = new Ingredientes();
		
		try {
			result = miIngrediente.save(model);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public List<Ingredientes> guardarAllIngredientes(List<Ingredientes> ingredientes) {
		List<Ingredientes> result = new ArrayList<Ingredientes>();
		
		try {
			result = miIngrediente.saveAll(ingredientes);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public Ingredientes getOneIngredientes(Integer id) {
		Ingredientes retrieved = new Ingredientes();
        try {
            Optional<Ingredientes> ingrediente = miIngrediente.findById(id);
            retrieved = ingrediente.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe ingrediente con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne almacen: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Ingredientes> getAllIngredientes() {
		List<Ingredientes> result = new ArrayList<Ingredientes>();
		try {
			result = miIngrediente.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	public void deleteByIdIngredientes (Integer id) {
		try {
			miIngrediente.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
