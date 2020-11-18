package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Receta;
import curso.menu.repository.RecetaRepository;

@Service
public class RecetaService {
	
	@Autowired
	private RecetaRepository miReceta;
	
	public Receta guardarReceta(Receta model) {
		Receta result = new Receta();
		
		try {
			result = miReceta.save(model);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public List<Receta> guardarAllRecetas(List<Receta> recetas) {
		List<Receta> result = new ArrayList<Receta>();
		
		try {
			result = miReceta.saveAll(recetas);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public Receta getOneReceta(Integer id) {
		Receta retrieved = new Receta();
        try {
            Optional<Receta> receta = miReceta.findById(id);
            retrieved = receta.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe receta con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne receta: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Receta> getAllRecetas() {
		List<Receta> result = new ArrayList<Receta>();
		try {
			result = miReceta.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	public void deleteByIdReceta(Integer id) {
		try {
			miReceta.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
