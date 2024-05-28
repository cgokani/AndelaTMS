package com.andela.tms.repositories.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andela.tms.entities.User;
import com.andela.tms.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> allUsers() {
		List<User> users = new ArrayList<>();

		userRepository.findAll().forEach(users::add);

		return users;
	}
}