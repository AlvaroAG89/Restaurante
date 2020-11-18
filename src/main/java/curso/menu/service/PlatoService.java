package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Categoria;
import curso.menu.model.Plato;
import curso.menu.repository.PlatoRepository;


@Service
public class PlatoService {
	
	@Autowired
	private PlatoRepository miPlato;
	
	public Plato guardarPlato(Plato model) {
		Plato result = new Plato();
		
		try {
			result = miPlato.save(model) ;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public List<Plato> guardarAllPlatos(List<Plato> platos) {
		List<Plato> result = new ArrayList<Plato>();
		
		try {
			result = miPlato.saveAll(platos);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public Plato getOnePlato(Integer id) {
		Plato retrieved = new Plato();
        try {
            Optional<Plato> plato = miPlato.findById(id);
            retrieved = plato.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe plato con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne plato: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Plato> getAllPlatos() {
		List<Plato> result = new ArrayList<Plato>();
		try {
			result = miPlato.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	public void deleteByIdPlato (Integer id) {
		try {
			miPlato.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
//	public List<Plato> findPlatoByCategoria(int categoria) {
//		return miPlato.findByCategoria(categoria);
//	}
	public List<Plato> findPlatoByCategoria(Categoria categoria) {
	return miPlato.findByMiCategoria(categoria);
}
	
}
