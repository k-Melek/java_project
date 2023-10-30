package com.melek.javaproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melek.javaproject.models.LoginUser;
import com.melek.javaproject.models.User;
import com.melek.javaproject.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userServ; 
	
//	@GetMapping("/users")
//	public ResponseEntity<?> allUsers(){
//		System.out.println("testetstets  : "+ userServ.allUsers());
//		return ResponseEntity.ok().body(userServ.allUsers());
//	} 
	
	@GetMapping("/users")
	public ResponseEntity<?> allUsers() {
	    try {
	        List<User> users = userServ.allUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception details
	        return new ResponseEntity<>("Error fetching users: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	@PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, BindingResult result) {
    	if(result.hasErrors()) {
    		System.out.println(result.getAllErrors());
    		return ResponseEntity.status(400).body(result.getAllErrors());
    	}
    	User savedUser = userServ.register(user, result);
//        return ResponseEntity.ok("User registered successfully!");
    	return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginUser logUser, BindingResult result) {
		User loggedUser = userServ.login(logUser, result);
    	if(result.hasErrors()) {
    		System.out.println(result.getAllErrors());
    		return ResponseEntity.status(400).body(result.getAllErrors());
    	}
    	return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> loggedUser(@PathVariable("id") Long user_id) {
		try {
	        User loggedUser = userServ.findUserById(user_id);
	        System.out.println("testetstetstestes  :"+loggedUser.getRole().getRoleName());
	        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace(); // Print the exception details to the console for debugging
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	
	
//	@GetMapping("/users/{id}")
//	public User oneStudent(@PathVariable Long id){
//		return userServ.findUserById(id);
//	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User newUser, BindingResult result, @PathVariable("id") Long user_id) {
		try {
	        User user = userServ.findUserById(user_id);
	        System.out.println("testetstetstestes  :"+ user.getRole().getRoleName());
	        user.setFirstName(newUser.getFirstName());
	        user.setLastName(newUser.getLastName());
	        user.setEmail(newUser.getEmail());
	        
	        User savedUser = userServ.updateUser(user);
	        
	        return new ResponseEntity<>(savedUser, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace(); // Print the exception details to the console for debugging
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	

}
