package curso.menu.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Almacen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Almacen")
	private Integer idAlmacen;
	
	@Column(name="INGREDIENTE")
	private String ingrediente;
	
	@Column(name="STOCK")
	private float stock;
	
	@Column(name="UNIDADES_STOCK")
	private String unidadesStock;
	
	/*
	@ManyToMany
    @JoinTable(name = "ingredientes_receta", 
        joinColumns = { @JoinColumn(name = "ID_Almacen") }, 
        inverseJoinColumns = { @JoinColumn(name = "ID_Receta") })
	private List<Receta> recetas;
	*/
	
	@OneToMany(mappedBy="miAlmacen")
	@JsonIgnoreProperties(value = "miAlmacen")
	private List<Ingredientes> misIngredientes;
	
	public Integer getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public String getUnidadesStock() {
		return unidadesStock;
	}

	public void setUnidadesStock(String unidadesStock) {
		this.unidadesStock = unidadesStock;
	}

	public List<Ingredientes> getMisIngredientes() {
		return misIngredientes;
	}

	public void setMisIngredientes(List<Ingredientes> misIngredientes) {
		this.misIngredientes = misIngredientes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public List<Receta> getRecetas() {
//		return recetas;
//	}
//
//	public void setRecetas(List<Receta> recetas) {
//		this.recetas = recetas;
//	}
	
	
	
	
}
