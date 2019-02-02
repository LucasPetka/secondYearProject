package group9rcraggs.application.repository;


import org.springframework.data.repository.CrudRepository;

import group9rcraggs.application.domain.Website;


public interface WebsiteRepository extends CrudRepository<Website, Integer> {
	Website findById(int id);
}