package curso.menu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Authorities")
public class Role implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String authority;

//	@ManyToOne
//	@JsonIgnoreProperties(value = "roles")
//	private Empleado miRol;
	
	@OneToOne
	@JsonIgnoreProperties(value = "rol")
	private Empleado empleado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", authority=" + authority + ", miRol=" + empleado + "]";
	}
	

	

}
