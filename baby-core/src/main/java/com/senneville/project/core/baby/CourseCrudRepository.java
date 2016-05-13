package com.senneville.project.core.baby;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCrudRepository extends CrudRepository<Course, Long> {

	List<Course> findByName(String name);
	List<Course> findByClients(Set<Client> clients);
}
