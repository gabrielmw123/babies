package com.senneville.project.web.command.line;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senneville.project.core.baby.Client;
import com.senneville.project.core.baby.ClientCrudRepository;
import com.senneville.project.core.baby.Course;
import com.senneville.project.core.baby.CourseCrudRepository;

@Component
public class BabyDataGenerator {

	final static String[] FIRST_NAMES = {
		"Aravane",
		"Timo",
		"Gabriel",
		"Anne"
		};
	final static String[] LAST_NAMES = {
		"Morales",
		"Massacré",
		"Morales",
		"Morales"
		};
    final static String[] COURSES = {
        "BB1",
        "BB2",
        "BB3",
        "BB4"
        };
	
	@Autowired
	private ClientCrudRepository clientCrudRepository;
	
	@Autowired
	private CourseCrudRepository courseCrudRepository;
	
	public void create() {
		IntStream.range(0, 4).forEach(i -> {
		    addClient(FIRST_NAMES[i], LAST_NAMES[i]);
		    addCourse(COURSES[i]);
		});
/**
		Client a = clientCrudRepository.findByFirstName("Aravane").get(0);
		Course one = courseCrudRepository.findByName("BB1").get(0);
		a.addCourse(one);
		clientCrudRepository.save(a);
	*/
	}

	private void addCourse(String name) {
        Course course = new Course();
        course.setName(name);
        this.courseCrudRepository.save(course);
    }

    private void addClient(String firstName, String lastName) {
		Client client = new Client();
		client.setFirstName(firstName);
		client.setLastName(lastName);
		clientCrudRepository.save(client);
	}
}
