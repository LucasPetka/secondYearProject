package group9rcraggs.application.repository;

import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.Role;



public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findById(int id);
	Role findByRole(String roleName);
}