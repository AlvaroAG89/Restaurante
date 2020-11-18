package curso.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.menu.model.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

}
