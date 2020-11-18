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

import curso.menu.model.Categoria;
import curso.menu.model.Plato;
import curso.menu.service.CategoriaService;
import curso.menu.service.PlatoService;

@Controller
@RequestMapping(value="/Plato")
public class PlatoController {

	@Autowired
	public PlatoService plaService;
	
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
	
	//--------- VER DETALLES DEL PLATO ----------//
	
	@GetMapping(value = "/verPlato/{id}")
	public String verPlato(@PathVariable(value="id") Integer id, Map<String, Object> model) {
		
		Plato plato = plaService.getOnePlato(id);
		
		
		if(plato == null) {
			return "redirect:/";
		}
		
		System.out.println(plato.getNombre());
		
		model.put("plato", plato);
		model.put("titulo", "Detalle del plato " + plato.getNombre());
		
		return "verPlato";
		
	}
	
	@GetMapping(value = "/{idCategoria}")
    public String platoCategoria(@PathVariable(value="idCategoria") Integer categoria, Map<String, Object> model) {

        Plato plato = new Plato();
        List<Plato> misPlatos = new ArrayList<Plato>();

        misPlatos =  plaService.getAllPlatos();

        // no esta controlado el error si no hay platos en la categoria
        for (int i = 0 ; i < misPlatos.size() ;i++) {
            plato = misPlatos.get(i);

            if (plato.getMiCategoria().getIdCategoria() == categoria) {
                i = misPlatos.size() + 1;
            }
        }
        
        
        
        model.put("platocat", plato);
        model.put("platos", misPlatos);
        model.put("idCat",categoria);

        return "platoCategoria";
	}
	
	@GetMapping(value = "/menu")
	public String platoMenu(Map<String, Object> model) {

		
		List<Plato> misPlatos = new ArrayList<Plato>();
		
		misPlatos =  plaService.getAllPlatos();
		
				
		model.put("platos", misPlatos);
		
		return "platoMenu";
				
	}
	
	@PostMapping(value="/save")
	public Plato savePlato(@RequestBody Plato plato) {
		
		plato = plaService.guardarPlato(plato);
		
		return plato;
		
	}
	
	@PostMapping(value="/saveAll")
	public List<Plato> saveAllPlatos(@RequestBody List<Plato> platos) {
		
		List<Plato> retrieved = new ArrayList<Plato>();
		
		retrieved = plaService.guardarAllPlatos(platos);
		
		return retrieved;
		
	}
	
	
	@GetMapping(value = "/get/{id}")
	public Plato getOnePlato(@PathVariable Integer id) {
		Plato retrieved = new Plato();
		try {
			retrieved = plaService.getOnePlato(id);
		}catch(Exception ex) {
			System.out.println("getPlato exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@GetMapping(value = "/getAll")
	public List<Plato> getAllPlatos(){
		List<Plato> retrieved = new ArrayList<Plato>();
		try {
			retrieved = plaService.getAllPlatos();
		}catch(Exception ex) {
			System.out.println("getAllPlatos exception:: " + ex.getLocalizedMessage());
		}
		return retrieved;
	}
	
	@DeleteMapping(value = "delete/{id}")
	public void deletePlato(@PathVariable Integer id) {
		try {
			plaService.deleteByIdPlato(id);;
		}catch(Exception ex) {
			System.out.println("deletePlato exception:: " + ex.getLocalizedMessage());
		}
	}
	
	
}
