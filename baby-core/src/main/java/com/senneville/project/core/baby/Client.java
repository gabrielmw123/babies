package com.senneville.project.core.baby;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
//@ToString
public class Client {

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column(nullable = false)
	private String firstName;

	@Getter
	@Setter
	@Column(nullable = false)
	private String lastName;
	
	@Getter
	@ManyToMany(mappedBy = "clients")
	private Set<Course> courses = new HashSet<Course>();
	
	public void addCourse(Course course) {
	    this.courses.add(course);
	    if (!course.getClients().contains(this)) {
	        course.addClient(this);
	    }
	}
	
	public boolean removeCourse(Course course) {
	    if (this.courses.remove(course)) {
	        course.removeClient(this);
	        return true;
	    }
	    return false;
	}
}
