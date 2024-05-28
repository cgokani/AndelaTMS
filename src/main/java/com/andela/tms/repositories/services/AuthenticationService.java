package com.andela.tms.repositories.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.andela.tms.dtos.LoginUserDto;
import com.andela.tms.dtos.RegisterUserDto;
import com.andela.tms.entities.Role;
import com.andela.tms.entities.RoleEnum;
import com.andela.tms.entities.User;
import com.andela.tms.repositories.RoleRepository;
import com.andela.tms.repositories.UserRepository;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;
	
	private final RoleRepository roleRepository;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public User signup(RegisterUserDto input) {
		
		 Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
	        
		    if (optionalRole.isEmpty()) {
		        return null;
		    }
		
		User user = new User();

		user.setFullName(input.getFullName());
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setRole(optionalRole.get());

		return userRepository.save(user);
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail()).orElseThrow();
	}

}
