package curso.menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import curso.menu.model.Categoria;
import curso.menu.service.CategoriaService;



@Controller
public class CategoriaController {

	@Autowired
	public CategoriaService catService;
	
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
	
	
	
	@GetMapping(value = "/empleado/verCategorias")
	public String verPlato(Map<String, Object> model) {
		
		List<Categoria> misCategorias = new ArrayList<Categoria>();
		
		misCategorias = catService.getAllCategorias();

		
		model.put("categorias", misCategorias);
		
		return "categoria/listaCategorias";
		
	}
	
	
	
	
	
	
	
	
	
	

	
}
