package com.example.student_instructor.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Students
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min =1)
	@Column(name="first_name")
	private String first_name;

	@NotNull
	@Size(min =1)
	@Column(name="last_name")
	private String last_name;

	@NotNull
	@Size(min =1)
	@Column(name="Contactnumber")
	private String Contactnumber;

	@Column(name="email")
	private String email;


//	@ManyToMany()
//	private Set<Courses> courses;

	@ManyToMany()
	private Set<Instructors>instructor;        //Students has instructors



	public Students()
	{
	}

	public Students(String first_name, String last_name, String contactnumber, String email)
	{
		this.first_name = first_name;
		this.last_name = last_name;
		Contactnumber = contactnumber;
		this.email = email;
		this.instructor = new HashSet<Instructors>();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
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

	public String getContactnumber()
	{
		return Contactnumber;
	}

	public void setContactnumber(String contactnumber)
	{
		Contactnumber = contactnumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

//	public Set<Courses> getCourses()
//	{
//		return courses;
//	}
//
//	public void setCourses(Set<Courses> courses)
//	{
//		this.courses = courses;
//	}

	public Set<Instructors> getInstructor()
	{
		return instructor;
	}

	public void setInstructor(Set<Instructors> instructors)
	{
		this.instructor = instructor;
	}

	public void addInstructor(Instructors instructor) {this.instructor.add(instructor);}
}
