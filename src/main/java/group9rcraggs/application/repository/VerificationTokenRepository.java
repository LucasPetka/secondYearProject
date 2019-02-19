package group9rcraggs.application.repository;



import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.VerificationToken;



public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Integer>{
	VerificationToken findByToken(String token);
	VerificationToken findByUser(User owner);
}