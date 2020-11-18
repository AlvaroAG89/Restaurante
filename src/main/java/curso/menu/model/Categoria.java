package curso.menu.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CATEGORIA")
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Integer idCategoria;
	
	@Column(name = "NOMBRE_CATEGORIA")
	private String nombreCategoria;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name="FOTO_CATEGORIA")
	private String fotoCategoria;
	
	@ManyToOne
	@JsonIgnoreProperties(value = "misCategorias")
	private Empleado miJefe;
	
	@OneToMany(mappedBy="miCategoria")
	@JsonIgnoreProperties(value = "miCategoria")
	private List<Plato> misPlatos;

	
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Empleado getMiJefe() {
		return miJefe;
	}

	public void setMiJefe(Empleado miJefe) {
		this.miJefe = miJefe;
	}

	public List<Plato> getMisPlatos() {
		return misPlatos;
	}

	public void setMisPlatos(List<Plato> misPlatos) {
		this.misPlatos = misPlatos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFotoCategoria() {
		return fotoCategoria;
	}

	public void setFotoCategoria(String fotoCategoria) {
		this.fotoCategoria = fotoCategoria;
	}
	
	
	

}
