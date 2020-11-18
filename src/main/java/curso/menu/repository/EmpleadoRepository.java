package curso.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	public Empleado findByUsername(String username);
}
