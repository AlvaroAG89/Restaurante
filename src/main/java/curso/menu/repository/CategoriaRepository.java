package curso.menu.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	
}
