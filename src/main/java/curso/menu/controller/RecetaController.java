package curso.menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.menu.model.Receta;
import curso.menu.model.Almacen;
import curso.menu.model.Ingredientes;
import curso.menu.service.AlmacenService;
import curso.menu.service.RecetaService;


@Controller
@RequestMapping(value="/Receta")
public class RecetaController {

	@Autowired
	public RecetaService recService;
	
	@Autowired
	public AlmacenService almService;
	
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
	@SuppressWarnings("unused")
	@GetMapping(value="/verReceta/{id}/comprobar")
	public String comprobarAlmacen(@PathVariable(value="id") Integer id, Map<String, Object> model) {
		
		Receta receta = recService.getOneReceta(id);
		List<Ingredientes> ingredientes = receta.getMiIngrediente();
		Almacen almacen = new Almacen();
		
		String ok = "";
		if(receta == null) {
			model.put("ok", ok);
			return "redirect:/";
		}
		
		List<String> ingredientesReceta = new ArrayList<String>();
		for (Ingredientes ingrediente : ingredientes) {
			almacen = (ingrediente.getMiAlmacen());
			ingredientesReceta.add(almacen.getIngrediente());
		}
		
		String check = "";
		model.put("ingredientes",ingredientesReceta);
		model.put("receta", receta);
		model.put("titulo", "Detalle de la receta de " + receta.getNombre());
		
		
		for (Ingredientes ingrediente : ingredientes) {
			almacen = (ingrediente.getMiAlmacen());
			if (almacen.getStock() - ingrediente.getCantidad() < 0) {
				check = ("No se puede realizar la receta, falta: " + almacen.getIngrediente());
				model.put("ok", ok);
				model.put("comprobar", check);
				return "verReceta";
			}
		}
		check = "Se puede realizar el plato";
		ok = "si";
		model.put("comprobar", check);
		model.put("ok", ok);
		System.out.println(check);
		return "verReceta";
	}
	
	@GetMapping(value="/verReceta/{id}/realizar")
	public String realizarReceta(@PathVariable(value="id") Integer id, Map<String, Object> model) {
		
		Receta receta = recService.getOneReceta(id);
		
String ok = "";
		
		if(receta == null) {
			return "redirect:/";
		}
		
//		System.out.println(receta.getNombre());
		//detalles: que empleado hace esto o receta
		List<Ingredientes> ingredientes = receta.getMiIngrediente();
		Almacen almacen = new Almacen();
		List<String> ingredientesReceta = new ArrayList<String>();
		for (Ingredientes ingrediente : ingredientes) {
			almacen = (ingrediente.getMiAlmacen());
			ingredientesReceta.add(almacen.getIngrediente());
		}
		
		model.put("ingredientes",ingredientesReceta);
		model.put("receta", receta);
		model.put("titulo", "Detalle de la receta de " + receta.getNombre());
		model.put("ok", ok);
		
		for (Ingredientes ingrediente : ingredientes) {
			almacen = (ingrediente.getMiAlmacen());
			float resultante = almacen.getStock() - ingrediente.getCantidad();
			almacen.setStock(resultante);
			almacen = almService.guardarAlmacen(almacen);
		}
		String check = "Se ha realizado el plato";
		
		model.put("comprobar", check);
	
		return "verReceta";
	}
	
	@GetMapping(value = "/verReceta/{id}")
	public String verReceta(@PathVariable(value="id") Integer id, Map<String, Object> model) {
		
		Receta receta = recService.getOneReceta(id);
		
		String ok = "";
		
		if(receta == null) {
			return "redirect:/";
		}
		
//		System.out.println(receta.getNombre());
		//detalles: que empleado hace esto o receta
		List<Ingredientes> ingredientes = receta.getMiIngrediente();
		Almacen almacen = new Almacen();
		List<String> ingredientesReceta = new ArrayList<String>();
		for (Ingredientes ingrediente : ingredientes) {
			almacen = (ingrediente.getMiAlmacen());
			ingredientesReceta.add(almacen.getIngrediente());
		}
		
		model.put("ingredientes",ingredientesReceta);
		model.put("receta", receta);
		model.put("titulo", "Detalle de la receta de " + receta.getNombre());
		model.put("ok", ok);
		
		return "verReceta";
		
	}
	

	@PostMapping(value="/save")
	public Receta saveReceta(@RequestBody Receta receta) {
		
		receta = recService.guardarReceta(receta);
		
		return receta;
		
	}
	
	@PostMapping(value="/saveAll")
	public List<Receta> saveAllRecetas(@RequestBody List<Receta> recetas) {
		
		List<Receta> retrieved = new ArrayList<Receta>();
		
		retrieved = recService.guardarAllRecetas(recetas);
		
		return retrieved;
		
	}
	
	
	@GetMapping(value = "/get/{id}")
	public Receta getOneReceta(@PathVariable Integer id) {
		Receta retrieved = new Receta();
		try {
			retrieved = recService.getOneReceta(id);
		}catch(Exception ex) {
			System.out.println("getReceta exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@GetMapping(value = "/getAll")
	public List<Receta> getAllRecetas(){
		List<Receta> retrieved = new ArrayList<Receta>();
		try {
			retrieved = recService.getAllRecetas();
		}catch(Exception ex) {
			System.out.println("getAllRecetas exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@DeleteMapping(value = "delete/{id}")
	public void deleteReceta(@PathVariable Integer id) {
		try {
			recService.deleteByIdReceta(id);;
		}catch(Exception ex) {
			System.out.println("deleteReceta exception:: " + ex.getLocalizedMessage());
		}
	}
	
	
	
}
