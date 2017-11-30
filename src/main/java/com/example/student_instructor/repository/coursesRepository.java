package com.example.student_instructor.repository;

import com.example.student_instructor.entity.Courses;

import org.springframework.data.repository.CrudRepository;

public interface coursesRepository extends CrudRepository<Courses, Long>
{

}
