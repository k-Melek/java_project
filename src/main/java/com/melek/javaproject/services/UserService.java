package com.melek.javaproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.melek.javaproject.models.LoginUser;
import com.melek.javaproject.models.User;
import com.melek.javaproject.repositories.RoleRepository;
import com.melek.javaproject.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private RoleRepository roleRep;

	public User register(User user, BindingResult result) {
		Optional<User> maybeUser = userRep.findByEmail(user.getEmail());
		if (maybeUser.isPresent()) {
			result.rejectValue("email", "regError", "Email already taken!");
		}
		if (!user.getPassword().equals(user.getConfirm())) {
			result.rejectValue("password", "regError", "Password and Confirm password must match :)");
		}
		if (result.hasErrors()) {
			return null;
		}
		String hashedPW = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPW);

		user.setRole(roleRep.findByRoleName("ROLE_USER"));

		user = userRep.save(user);
		return user;
	}

	public User login(LoginUser logUser, BindingResult result) {
		Optional<User> maybeUser = userRep.findByEmail(logUser.getEmail());
		if (!maybeUser.isPresent()) {
			result.rejectValue("email", "logError", "Invalid Email!");
		}
		User user = maybeUser.get();
		if (!BCrypt.checkpw(logUser.getPassword(), user.getPassword())) {
			result.rejectValue("password", "logError", "Incorrect Password");
		}
		if (result.hasErrors()) {
			return null;
		}

		return user;
	}

	// Find User By Id
	public User findUserById(Long id) {
		Optional<User> maybeUser = userRep.findById(id);
		if (maybeUser.isPresent()) {
			return maybeUser.get();
		}
		return null;
	}

	// Find all users
	public List<User> allUsers() {
		return userRep.findAll();
	}

	// Update user
	public User updateUser(User user) {
		return userRep.save(user);
	}

}
