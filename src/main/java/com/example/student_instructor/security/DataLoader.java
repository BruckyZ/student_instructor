package com.example.student_instructor.security;

import com.example.student_instructor.entity.*;
import com.example.student_instructor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository appRoles;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @Autowired
    coursesRepository CoursesRepository;

    @Autowired
    studentsRepository StudentsRepository;

    @Autowired
    instructorsRepository InstructorsRepository;

    @Override
    public void run(String... strings) throws Exception {
//        creating a Admin user account with username="user" and password="password" the same will be shown in the database. So instead of creating in the database same can be done here/
        Role aRole = new Role();
        aRole.setRole("ADMIN");
        appRoles.save(aRole);
        System.out.println("Admin role has been created");

        User u = new User();
        u.setPassword("pass");
        u.setUsername("admin");
        u.setEmail("person@person.com");
        u.addRole(appRoles.findByRole("ADMIN"));
        userRepo.save(u);


//        Role bRole = new Role();
//        aRole.setRole("USER");
//        appRoles.save(bRole);
//        System.out.println("Admin role has been created");
//
//
//        User u1 = new User();
//        u1.setPassword("pass");
//        u1.setUsername("user");
//        u1.setEmail("person@person.com");
//        u1.addRole(appRoles.findByRole("USER"));
//        userRepo.save(u1);

        Courses courses=new Courses("Java","1","Java Programming course");
        CoursesRepository.save(courses);

        Instructors instructors=new Instructors("Brook", "Gebre", "504-451-1543","Brookz@gmail.com");
        instructors.addCourse(courses);
        InstructorsRepository.save(instructors);

        Students students=new Students("Bruck", "Zewge", "202-451-1543","bz@gmail.com");
        students.addInstructor(instructors);
        StudentsRepository.save(students);

    }
}
