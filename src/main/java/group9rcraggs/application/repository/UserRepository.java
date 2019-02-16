package group9rcraggs.application.repository;


import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findById(int id);
    User findByLogin(String login);
    boolean existsByLogin(String login);
}