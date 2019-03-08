package group9rcraggs.application.repository;

import org.springframework.data.repository.CrudRepository;
import group9rcraggs.application.domain.Plan;



public interface PlanRepository extends CrudRepository<Plan, Integer> {
	Plan findById(int id);
}