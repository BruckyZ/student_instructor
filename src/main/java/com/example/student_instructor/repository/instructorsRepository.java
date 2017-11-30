package com.example.student_instructor.repository;

import com.example.student_instructor.entity.Instructors;
import org.springframework.data.repository.CrudRepository;

public interface instructorsRepository extends CrudRepository<Instructors, Long>
{

}
