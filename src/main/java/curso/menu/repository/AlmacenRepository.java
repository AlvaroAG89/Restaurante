package curso.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer>{

}
