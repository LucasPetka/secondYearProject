package group9rcraggs.application.repository;

import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.AlertAfter;


public interface AlertAfterRepository extends CrudRepository<AlertAfter, Integer> {
	AlertAfter findById(int id);
}