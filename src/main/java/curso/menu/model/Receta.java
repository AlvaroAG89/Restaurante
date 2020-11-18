package curso.menu.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Receta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Receta")
	private Integer idReceta;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column(name = "IMAGEN")
	private String imagen;

	@OneToOne
	@JsonIgnoreProperties(value = "receta")
	private Plato plato;
	
//	@ManyToMany (mappedBy="recetas")
//	private List<Almacen> ingredientes;

	@OneToMany(mappedBy="miReceta")
	@JsonIgnoreProperties(value = "miReceta")
	private List<Ingredientes> miIngrediente;
	
//	public List<Almacen> getIngredientes() {
//		return ingredientes;
//	}
//
//	public void setIngredientes(List<Almacen> ingredientes) {
//		this.ingredientes = ingredientes;
//	}

	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Integer getIdReceta() {
		return idReceta;
	}

	public List<Ingredientes> getMiIngrediente() {
		return miIngrediente;
	}

	public void setMiIngrediente(List<Ingredientes> miIngrediente) {
		this.miIngrediente = miIngrediente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripción(String descripcion) {
		this.descripcion = descripcion;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
	
	
}
