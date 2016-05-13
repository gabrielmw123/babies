package com.senneville.project.core.baby;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long> {

	List<Client> findByLastName(String lastName);
	List<Client> findByFirstName(String firstName);
	List<Client> findByCourses(Set<Course> courses);
}
