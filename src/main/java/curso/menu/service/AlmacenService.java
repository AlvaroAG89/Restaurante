package curso.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.menu.model.Almacen;
import curso.menu.repository.AlmacenRepository;

@Service
public class AlmacenService {

	@Autowired
	private AlmacenRepository miAlmacen;
	
	public Almacen guardarAlmacen(Almacen model) {
		Almacen result = new Almacen();
		
		try {
			result = miAlmacen.save(model);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public List<Almacen> guardarAllAlmacenes(List<Almacen> almacenes) {
		List<Almacen> result = new ArrayList<Almacen>();
		
		try {
			result = miAlmacen.saveAll(almacenes);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
	
	public Almacen getOneAlmacen(Integer id) {
		Almacen retrieved = new Almacen();
        try {
            Optional<Almacen> almacen = miAlmacen.findById(id);
            retrieved = almacen.get();
        }catch (NoSuchElementException exc) {
            System.out.println("No existe almacen con id " + id);
        }catch (Exception ex) {
            System.out.println("Error en getOne almacen: " + ex.getMessage());
        }
        return retrieved;
    }
	
	
	public List<Almacen> getAllAlmacen() {
		List<Almacen> result = new ArrayList<Almacen>();
		try {
			result = miAlmacen.findAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
	public void deleteByIdAlmacen (Integer id) {
		try {
			miAlmacen.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
