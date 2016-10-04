package com.rsh.project.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonBackReference
	private List<ProjectUserAssignment> projectUserAssignmentList;

	@SuppressWarnings("unused")
	public User(){}
	
	public User(String first, String last){
		this.setFirstName(first);
		this.setLastName(last);
	}
	
	public User(String first, String last, String email){
		this.setFirstName(first);
		this.setLastName(last);
		this.setEmail(email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}