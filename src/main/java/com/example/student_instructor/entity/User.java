package com.example.student_instructor.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	private String email;

	private String password;

	private String first_name;

	private String last_name;
	private String enabled;

	private String username;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public User() {
		this.roles=new HashSet<>();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirst_name()
	{
		return first_name;
	}

	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}

	public String getLast_name()
	{
		return last_name;
	}

	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}

	public String getEnabled()
	{
		return enabled;
	}

	public void setEnabled(String enabled)
	{
		this.enabled = enabled;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public void addRole(Role role)
	{
		this.roles.add(role);
	}
}
