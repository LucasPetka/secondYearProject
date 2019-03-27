package group9rcraggs.application.repository;



import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.PasswordResetToken;
import group9rcraggs.application.domain.User;



public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer>{
	PasswordResetToken findByToken(String token);
	PasswordResetToken findByUser(User owner);
	boolean existsByToken(String token);
}