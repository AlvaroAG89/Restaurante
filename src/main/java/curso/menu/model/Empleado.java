package curso.menu.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Empleado")
public class Empleado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPLEADO")
	private Integer idEmpleado;
	
	
	@Column(unique=true, name = "USERNAME") 
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	
	@Column(name = "NOMBRE_EMPLEADO")
	private String nombreEmpleado;
	
	@Column(name = "APELLIDO_EMPLEADO")
	private String apellidoEmpleado;

	@Column(name = "FOTO_EMPLEADO")
	private String fotoEmpleado;
	
	@OneToMany(mappedBy="miJefe")
	@JsonIgnoreProperties(value = "miJefe")
	private List<Categoria> misCategorias;

	
	//en joincolumn se hace referencia a la columna de la tabla
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "empleado_id")
//	private List<Role> roles;
	
	@OneToOne(mappedBy="empleado")
	@JsonIgnoreProperties(value = "empleado")
	private Role rol;
	
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}

	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}

	public List<Categoria> getMisCategorias() {
		return misCategorias;
	}

	public void setMisCategorias(List<Categoria> misCategorias) {
		this.misCategorias = misCategorias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFotoEmpleado() {
		return fotoEmpleado;
	}

	public void setFotoEmpleado(String fotoEmpleado) {
		this.fotoEmpleado = fotoEmpleado;
	}

	

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", nombreEmpleado=" + nombreEmpleado + ", apellidoEmpleado="
				+ apellidoEmpleado + ", fotoEmpleado=" + fotoEmpleado + ", misCategorias=" + misCategorias + " roles=" + rol + "]";
	}
	
	
	
	
	
}
