package group9rcraggs.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.Notifications;
import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.User;



public interface NotificationsRepository extends CrudRepository<Notifications, Integer> {

	Notifications findById(int id);
	List<Notifications> findByTitle(String title);
	List<Notifications> findByOwner(User owner);
	List<Notifications> findByPage(Page page);
	
}
