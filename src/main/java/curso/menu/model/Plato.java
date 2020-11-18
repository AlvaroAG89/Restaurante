package curso.menu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Plato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PLATO")
	private Integer idPlato;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "DIFICULTAD")
	private Integer dificultad;

	@Column(name = "IMAGEN")
	private String imagen;
	
	@Column(name = "PRECIO_FINAL")
	private Float precioFinal;
	

	@ManyToOne
	@JsonIgnoreProperties(value = "misPlatos")
	private Categoria miCategoria;

	@OneToOne(mappedBy="plato")
	@JsonIgnoreProperties(value = "plato")
	private Receta receta;

	public Integer getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Float getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Float precioFinal) {
		this.precioFinal = precioFinal;
	}


	public Categoria getMiCategoria() {
		return miCategoria;
	}

	public void setMiCategoria(Categoria miCategoria) {
		this.miCategoria = miCategoria;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Plato [idPlato=" + idPlato + ", nombre=" + nombre + ", descripcion=" + descripcion + ", dificultad="
				+ dificultad + ", imagen=" + imagen + ", precioFinal=" + precioFinal + ", miCategoria=" + miCategoria
				+ ", receta=" + receta + "]";
	}
	
	

	
}
