package group9rcraggs.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.Page;


public interface PageRepository extends CrudRepository<Page, Integer> {
	Page findById(int id);
	List<Page> findByOwner(int id);
}