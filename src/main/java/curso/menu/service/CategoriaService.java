package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Categoria;
import curso.menu.repository.CategoriaRepository;

@Service
public class CategoriaService {


	@Autowired
	private CategoriaRepository miCategoria;
	
	public Categoria guardarCategoria(Categoria model) {
		Categoria result = new Categoria();
		
		try {
			result = miCategoria.save(model) ;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public List<Categoria> guardarAllCategorias(List<Categoria> categorias) {
		List<Categoria> result = new ArrayList<Categoria>();
		
		try {
			result = miCategoria.saveAll(categorias);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public Categoria getOneCategoria(Integer id) {
		Categoria retrieved = new Categoria();
        try {
            Optional<Categoria> categoria = miCategoria.findById(id);
            retrieved = categoria.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe categoria con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne categoria: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Categoria> getAllCategorias() {
		List<Categoria> result = new ArrayList<Categoria>();
		try {
			result = miCategoria.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	public void deleteByIdCategoria (Integer id) {
		try {
			miCategoria.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
}
