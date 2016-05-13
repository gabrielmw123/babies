package com.senneville.project.core.baby;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
//@ToString
public class Course {

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column(nullable = false)
	private String name;
	
	@Getter
    @ManyToMany
    @JoinTable(
        name = "client_courses",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    )
    private Set<Client> clients = new HashSet<Client>();
	
	public void addClient(Client client) {
	    this.clients.add(client);
	    if (!client.getCourses().contains(this)) {
	        client.addCourse(this);
	    }
	}
	
	public boolean removeClient(Client client) {
	    if (this.clients.remove(client)) {
	        client.getCourses().remove(this);
	        return true;
	    }
	    return false;
	}
}
