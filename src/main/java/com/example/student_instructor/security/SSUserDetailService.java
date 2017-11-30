package com.example.student_instructor.security;


import com.example.student_instructor.entity.Role;
import com.example.student_instructor.entity.User;
import com.example.student_instructor.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Transactional
@Service
public class SSUserDetailService implements UserDetailsService
{
	private UserRepository userRepository;

	public SSUserDetailService(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try{
			User user = userRepository.findByUsername(username);
			if(user==null)
			{
				return null;
			}

			System.out.println("A user has been found:"+user.getUsername());
			System.out.println("user login detail"+user.getPassword());
			System.out.println("user login detail"+getAuthorities(user));
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));

		}catch (Exception e)

		{

			throw new UsernameNotFoundException("User not found");
		}

	}


/*	private Set<GrantedAuthority> getAuthorities(User user) {
		System.out.println("Entered getAuthorities");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		System.out.println(user.getRoles());

		for(Role eachRole:user.getRoles())
		{
			System.out.println("user permission");
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(eachRole.getRole());
			authorities.add(grantedAuthority);
			System.out.println("Granted Authority"+eachRole.getRole());
		}
		return authorities;
	}*/


	public Set<GrantedAuthority> getAuthorities(User user) {

		Set <GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		for(Role role : user.getRoles() )
		{
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
			System.out.println("Granted Authority"+role.getRole());
		}
		return authorities;
	}
}