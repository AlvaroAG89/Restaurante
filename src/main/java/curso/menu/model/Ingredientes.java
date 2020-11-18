package curso.menu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Ingredientes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Ingredientes")
	private Integer idIngredientes;
	
	@ManyToOne
	@JsonIgnoreProperties(value = "miIngrediente")
	private Receta miReceta;
 
    @ManyToOne
    @JsonIgnoreProperties(value = "misIngredientes")
    private Almacen miAlmacen;
	
	@Column(name="Cantidad")
	private float cantidad;

	// Tipo de cantidad??? kgs uds litros etc
	
	
	
	public Integer getIdIngredientes() {
		return idIngredientes;
	}

	public Receta getMiReceta() {
		return miReceta;
	}

	public void setMiReceta(Receta miReceta) {
		this.miReceta = miReceta;
	}

	public Almacen getMiAlmacen() {
		return miAlmacen;
	}

	public void setMiAlmacen(Almacen miAlmacen) {
		this.miAlmacen = miAlmacen;
	}

	public void setIdIngredientes(Integer idIngredientes) {
		this.idIngredientes = idIngredientes;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
