package com.example.student_instructor.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
	public Role() {
	}

	public Role(String role) {
		this.role=role;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;


	//Can only have one role or use @Unique mean the sting shouldnt be replicated inside database in any other row
	@Column(unique=true)
	private String role;

	//Telling you what its is mapped by looked in the entity of this type USERDATE and find the table to get the
	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	private Set<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


}
