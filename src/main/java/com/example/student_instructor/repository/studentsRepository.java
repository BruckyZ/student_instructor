package com.example.student_instructor.repository;

import com.example.student_instructor.entity.Instructors;
import com.example.student_instructor.entity.Students;
import org.springframework.data.repository.CrudRepository;

public interface studentsRepository extends CrudRepository<Students, Long>
{
//  Iterable<Students> findAllByContactnumberContaining(String fN);
  Iterable<Students> findAllByEmailContains(String cN);
}

