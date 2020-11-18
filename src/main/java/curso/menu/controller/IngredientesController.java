package curso.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.menu.model.Ingredientes;
import curso.menu.service.IngredientesService;

@Controller
@RequestMapping(value="/Ingredientes")
public class IngredientesController {

	
	@Autowired
	public IngredientesService ingService;
	
	/*
	//Esto es para que thymeleaf almacene los datos en una estructura que le sea familiar y luego
	//en el save lo recoja. Debe estar mapeado igual que el save
	@RequestMapping(value="/formulario")	
	public String crear(Map<String, Object> model) {
		// Nombre del parametro es String, y el objeto que se guarda Object

		Almacen empleado = new Almacen();
		//nombre que usaremos para tratarlo en el html, y lo que contiene.
		model.put("empleado", empleado);
		model.put("titulo", "Formulario de Empleado");
		return "formulario";
	}
	
	//Recoge lo que enviamos al HTML con thymeleaf en el metodo anterior y lo trata
	@PostMapping(value ="/formulario")
	public String saveAlmacen(@ModelAttribute("empleado") Almacen empleado, BindingResult result, 
			Model model) {
		
		//controlamos si viene con fallos
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "form";
		}
				
		empService.guardarAlmacen(empleado);

		
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo") String codigo, Model model) {
		
		//Comprobamos que existe y que lo elimine
		Empleado empleado = empService.getOneAlmacen(Integer.parseInt(codigo));

		if(empleado != null) {
			empService.deleteByIdAlmacen(Integer.parseInt(codigo));
		}
		
		return "redirect:/";
	}
	*/
	
	@PostMapping(value="/save")
	public Ingredientes saveIngredientes(@RequestBody Ingredientes empleado) {
		
		empleado = ingService.guardarIngredientes(empleado);
		
		return empleado;
		
	}
	
	@PostMapping(value="/saveAll")
	public List<Ingredientes> saveAllIngredientes(@RequestBody List<Ingredientes> empleados) {
		
		List<Ingredientes> retrieved = new ArrayList<Ingredientes>();
		
		retrieved = ingService.guardarAllIngredientes(empleados);
		
		return retrieved;
		
	}
	
	
	@GetMapping(value = "/get/{id}")
	public Ingredientes getOneAlmacen(@PathVariable Integer id) {
		Ingredientes retrieved = new Ingredientes();
		try {
			retrieved = ingService.getOneIngredientes(id);
		}catch(Exception ex) {
			System.out.println("getIngredientes exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@GetMapping(value = "/getAll")
	public List<Ingredientes> getAllIngredientes(){
		List<Ingredientes> retrieved = new ArrayList<Ingredientes>();
		try {
			retrieved = ingService.getAllIngredientes();
		}catch(Exception ex) {
			System.out.println("getAllIngredientes exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@DeleteMapping(value = "delete/{id}")
	public void deleteIngredientes(@PathVariable Integer id) {
		try {
			ingService.deleteByIdIngredientes(id);;
		}catch(Exception ex) {
			System.out.println("deleteIngredientes exception:: " + ex.getLocalizedMessage());
		}
	}
}
