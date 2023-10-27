package com.melek.javaproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melek.javaproject.models.User;
import com.melek.javaproject.services.UserService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userServ; 
	
	@GetMapping("/users")
	public ResponseEntity<Object> allUsers(){
		return ResponseEntity.ok().body(userServ.allUsers());
	} 
	
	
	@PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, BindingResult result) {
    	if(result.hasErrors()) {
    		System.out.println(result.getAllErrors());
    		return ResponseEntity.status(400).body(result.getAllErrors());
    	}
    	User savedUser = userServ.register(user, result);
        return ResponseEntity.ok().body(savedUser);
    }
	
	
	

}
