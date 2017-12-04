package com.example.student_instructor.entity;

//import com.example.week7_challenge.repositories.JobSeekerRepository;

import com.example.student_instructor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	coursesRepository CoursesRepository;

	@Autowired
	studentsRepository StudentsRepository;

	@Autowired
	instructorsRepository InstructorsRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public Long countByEmail(String email) {
		return userRepository.countByEmail(email);
	}
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

//	public void saveUserData(User user) {
//		user.addRole (roleRepository.findByRole("USER"));
//		user.setEnabled(true);
//		userRepository.save(user);
//	}

	public void saveAdmin(User user) {
		user.addRole (roleRepository.findByRole("ADMIN"));
		user.setEnabled(true);
		userRepository.save(user);
	}
}
