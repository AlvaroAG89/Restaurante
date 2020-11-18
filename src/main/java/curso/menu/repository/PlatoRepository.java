package curso.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import curso.menu.model.Categoria;
import curso.menu.model.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer>{
//    @Query("SELECT * FROM plato INNER JOIN categoria ON plato.mi_categoria_id_categoria = categoria.id_categoria WHERE categoria.nombre_categoria LIKE :categoriaElegida")
//    public List<Plato> findByCategoria(@Param("categoriaElegida") String categoriaElegida);
	
//	@Query("SELECT p.idPlato, p.nombre, p.descripcion, p.dificultad, p.imagen, p.precioFinal, p.miCategoria.idCategoria FROM Plato p WHERE p.miCategoria.idCategoria = :categoriaElegida")
//    public List<Plato> findByCategoria(@Param("categoriaElegida") int categoriaElegida);
	
	public List<Plato> findByMiCategoria(Categoria miCategoria);
}
