package curso.menu.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import curso.menu.model.Categoria;
import curso.menu.model.Empleado;
import curso.menu.model.Role;
import curso.menu.repository.RolesRepository;
import curso.menu.service.CategoriaService;
import curso.menu.service.EmpleadoService;
import curso.menu.service.RolesService;

@Controller
@RequestMapping(value="empleado")
public class EmpleadoController {
	
	@Autowired
	private RolesRepository rolRepo;
	
	@Autowired
	public EmpleadoService empService;
	
	@Autowired
	public RolesService rolService;
	
	//Para cifrar la contraseña
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/formularioEmpleado")	
	public String crear(Map<String, Object> model) {

		Empleado empleado = new Empleado();
		Role role = new Role();
		
		// Nombre del parametro es String, y el objeto que se guarda Object
		model.put("empleado", empleado);
		model.put("rol", role);
		model.put("titulo", "Crear Empleado");

		return "empleado/formularioEmpleado";
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping(value ="/formularioEmpleado")
	public String saveEmpleado(@ModelAttribute("empleado") Empleado empleado,
			@ModelAttribute("rol") Role rol, BindingResult result, 
			Model model, @RequestParam("file") MultipartFile foto) {
		
				
		//controlamos si viene con fallos
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleado");
			return "empleado/formularioEmpleado";
		}
		
		
		if(!foto.isEmpty()) {
			Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				//escribimos la imagen al directorio uploads
				Files.write(rutaCompleta, bytes);
				
				empleado.setFotoEmpleado(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (empleado.getIdEmpleado()!=null){ //Si no metemos foto, que rescate la antigua foto antes del guardado para no perderla
            Empleado antigua = empService.getOneEmpleado(empleado.getIdEmpleado());
            empleado.setFotoEmpleado(antigua.getFotoEmpleado());
        }
		
		//ciframos la contraseña antes de guardarla
		String bcryptPassword = passwordEncoder.encode(empleado.getPassword());
		//Se vuelve a almacenar pero ya cifrada
		empleado.setPassword(bcryptPassword);
		
		
		
		rolService.saveRole(rol, empleado);
		
		if (empleado.getIdEmpleado()!=null) {
		empleado.setRol(rol);
		empService.guardarEmpleado(empleado);
		} else {
		empService.guardarEmpleado(empleado);
		rol.setEmpleado(empleado);
		rolRepo.save(rol);
		empleado.setRol(rol);
		}
		
		
		
		
		
		
		
		return "redirect:/empleado/listaEmpleados";
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/formularioEmpleado/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
		Empleado empleado = new Empleado();

		if (id > 0) {
			empleado = empService.getOneEmpleado(id);
			if (empleado == null) {
			//	flash.addFlashAttribute("error", "El ID del empleado no existe en la BBDD");
				return "redirect:/empleado/listaEmpleados";
			}
		} else {
			//flash.addFlashAttribute("error", "El ID del empleado no puede ser 0");
			return "redirect:/empleado/listaEmpleados";
		}
		
		Role rol = empleado.getRol();
		
		
		model.put("rol", rol);
		model.put("empleado", empleado);
		model.put("titulo", "Editar empleado");
		
		return "empleado/formularioEmpleado";
	}
	

	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo") String codigo, Model model) {
		
		//Comprobamos que existe y que lo elimine
		Empleado empleado = empService.getOneEmpleado(Integer.parseInt(codigo));

		if(empleado != null) {
			empService.deleteByIdEmpleado(Integer.parseInt(codigo));
		}
		
		return "redirect:/empleado/listaEmpleados";
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/listaEmpleados")
	public String listaEmpleados(Map<String, Object> model) {
		
		List<Empleado> misEmpleados = new ArrayList<Empleado>();
		misEmpleados = empService.getAllEmpleado();
		
		
		if(misEmpleados == null) {
			return "redirect:/";
		}
		
		model.put("empleados", misEmpleados);
		model.put("titulo", "Lista de Empleados");
		
		return "empleado/listaEmpleados";
	}	
	
	
	
	//PARA EL FRONT, COMPROBACION DE ROLE PARA MOSTRAR SEGUN QUE COSAS
	
	public static boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		//esta coleccion lista cualquier tipo de objeto que herede de GrantedAuthority o la implemente
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); 
		
		//Si contiene el role pasado, devuelve un true
		return authorities.contains(new SimpleGrantedAuthority(role));
	}
	
	
	public static String myRole(Empleado empleado) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Role role = empleado.getRol();
		authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		
		return authorities.get(0).toString();
	}	
}
