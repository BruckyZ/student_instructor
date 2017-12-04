package com.example.student_instructor.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Courses
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min =1)

	private String title;

	@NotNull
	@Size(min =1)

	private String course_number;

	@NotNull
	@Size(min =1)

	private String description;

	@ManyToMany(mappedBy = "courses")
	private Set<Instructors> Instructor;               //Instructors has course


	public Courses(String title, String course_number, String description)
	{
		this.title = title;
		this.course_number = course_number;
		this.description = description;
		//Creating constructor instatiating for Set objects
		this.Instructor=new HashSet<Instructors>();
	}

	public Courses()
	{
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCourse_number()
	{
		return course_number;
	}

	public void setCourse_number(String course_number)
	{
		this.course_number = course_number;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Instructors> getInstructor()
	{
		return Instructor;
	}

	public void setInstructor(Set<Instructors> instructor)
	{
		Instructor = instructor;
	}
}