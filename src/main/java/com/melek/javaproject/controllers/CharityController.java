package com.melek.javaproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melek.javaproject.models.Address;
import com.melek.javaproject.models.Category;
import com.melek.javaproject.models.Charity;
import com.melek.javaproject.models.CharityAndAddressAndCategoriesRequest;
import com.melek.javaproject.services.AddressService;
import com.melek.javaproject.services.CharityService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CharityController {

	@Autowired
	private CharityService charityServ;
	
	@Autowired
	private AddressService addressServ;
	
	@GetMapping("/charities")
	public ResponseEntity<Object> allCharities(){
		return ResponseEntity.ok().body(charityServ.allCharities());
	} 
	
	@PostMapping("/charities/new")
	public ResponseEntity<Object> createCharity(@Valid @RequestBody CharityAndAddressAndCategoriesRequest request, BindingResult result) {
	    // Check for validation errors
	    if (result.hasErrors()) {
	        System.out.println(result.getAllErrors());
	        return ResponseEntity.status(400).body(result.getAllErrors());
	    }

	    // Extract charity, address, and category information from the request
	    Charity charity = request.getCharity();
	    Address address = request.getAddress();
	    List<Category> categories = request.getCategories();

	    //meeee
	    Address savedAddress = addressServ.createAddress(address);
	    
	    
	    // Set the Address and Categories to the Charity instance
	    charity.setAddress(savedAddress);
	    charity.setCategories(categories);

	    // Save the Charity instance using your service
	    Charity savedCharity = charityServ.createCharity(charity);

	    return new ResponseEntity<>(savedCharity, HttpStatus.OK);
	}
	
	
}
