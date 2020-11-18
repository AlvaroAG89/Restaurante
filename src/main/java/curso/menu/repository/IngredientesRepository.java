package curso.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Ingredientes;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Integer>{

}
