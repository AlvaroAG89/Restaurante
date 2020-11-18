package curso.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer>{

}
