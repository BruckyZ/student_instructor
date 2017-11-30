package com.example.student_instructor.security;

import com.example.student_instructor.entity.Role;
import com.example.student_instructor.entity.User;
import com.example.student_instructor.repository.RoleRepository;
import com.example.student_instructor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository appRoles;

    @Autowired
    UserRepository userRepo;

    @Override
    public void run(String... strings) throws Exception {
//        creating a Admin user account with username="user" and password="password" the same will be shown in the database. So instead of creating in the database same can be done here/
        Role aRole = new Role();
        aRole.setRole("ADMIN");
        appRoles.save(aRole);
        System.out.println("Admin role has been created");
        User user = new User();

        User u = new User();
        u.setPassword("pass");
        u.setUsername("user");
        u.setEmail("person@person.com");
        u.addRole(appRoles.findByRole("ADMIN"));
        userRepo.save(u);

    }
}
