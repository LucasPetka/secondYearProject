package group9rcraggs.application.repository;

import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.Email;
import group9rcraggs.application.domain.User;

public interface EmailRepository extends CrudRepository<Email, Integer> {
	Email findById(int id);
	Email findByOwner(User owner);
}