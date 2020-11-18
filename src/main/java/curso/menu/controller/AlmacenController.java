package curso.menu.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import curso.menu.model.Almacen;
import curso.menu.service.AlmacenService;

@Controller
@RequestMapping(value="/Almacen")
public class AlmacenController {

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
	
	@GetMapping(value = "/almacen")
	public String almacen(Map<String, Object> model) {
		
		List<Almacen> miAlmacen = new ArrayList<Almacen>();
		
		miAlmacen =  almService.getAllAlmacen();	
		
		model.put("almacen", miAlmacen);
		
		return "almacen/almacen";
		
		
	}
	
	@PostMapping(value="/save")
	public Almacen saveAlmacen(@RequestBody Almacen almacen) {
		
		almacen = almService.guardarAlmacen(almacen);
		
		return almacen;
		
	}
	
	@PostMapping(value="/saveAll")
	public List<Almacen> saveAllAlmacen(@RequestBody List<Almacen> almacenes) {
		
		List<Almacen> retrieved = new ArrayList<Almacen>();
		
		retrieved = almService.guardarAllAlmacenes(almacenes);
		
		return retrieved;
		
	}
	
	
	@GetMapping(value = "/get/{id}")
	public Almacen getOneAlmacen(@PathVariable Integer id) {
		Almacen retrieved = new Almacen();
		try {
			retrieved = almService.getOneAlmacen(id);
		}catch(Exception ex) {
			System.out.println("getAlmacen exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@GetMapping(value = "/getAll")
	public List<Almacen> getAllAlmacenes(){
		List<Almacen> retrieved = new ArrayList<Almacen>();
		try {
			retrieved = almService.getAllAlmacen();
		}catch(Exception ex) {
			System.out.println("getAllAlmacenes exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@DeleteMapping(value = "delete/{id}")
	public void deleteAlmacen(@PathVariable Integer id) {
		try {
			almService.deleteByIdAlmacen(id);;
		}catch(Exception ex) {
			System.out.println("deleteAlmacen exception:: " + ex.getLocalizedMessage());
		}
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value="/formularioAlmacen")	
	public String crear(Map<String, Object> model) {

		Almacen almacen = new Almacen();
		
		// Nombre del parametro es String, y el objeto que se guarda Object
		model.put("almacen", almacen);

		model.put("titulo", "Nuevo Ingrediente");

		return "almacen/formularioAlmacen";
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping(value ="/formularioAlmacen")
	public String saveAlmacen(@ModelAttribute("almacen") Almacen almacen, BindingResult result, 
			Model model) {
		
				
		//controlamos si viene con fallos
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de nuevo Ingrediente");
			return "almacen/formularioAlmacen";
		}
		
		
		
		System.out.println(almacen.toString());
		almService.guardarAlmacen(almacen);

		
		return "redirect:/Almacen/almacen";
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/formularioAlmacen/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
		Almacen almacen = new Almacen();

		if (id > 0) {
			almacen = almService.getOneAlmacen(id);
			if (almacen == null) {
			//	flash.addFlashAttribute("error", "El ID del almacen no existe en la BBDD");
				return "redirect:/almacen/almacen";
			}
		} else {
			//flash.addFlashAttribute("error", "El ID del almacen no puede ser 0");
			return "redirect:/almacen/almacen";
		}
		model.put("almacen", almacen);
		model.put("titulo", "Editar ingrediente");
		
		return "almacen/formularioAlmacen";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo") String codigo, Model model) {
		
		//Comprobamos que existe y que lo elimine
		Almacen almacen = almService.getOneAlmacen(Integer.parseInt(codigo));

		if(almacen != null) {
			almService.deleteByIdAlmacen(Integer.parseInt(codigo));
		}
		
		return "redirect:/Almacen/almacen";
	}
	
}
