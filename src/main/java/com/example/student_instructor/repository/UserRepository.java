package com.example.student_instructor.repository;


import com.example.student_instructor.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String username);
	User findByEmail(String username);
	Long countByEmail (String email);
//	Long CountByUsername (String username);


}
