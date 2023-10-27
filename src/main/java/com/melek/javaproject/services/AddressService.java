package com.melek.javaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melek.javaproject.models.Address;
import com.melek.javaproject.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepo;

	// CREATE
	public Address createAddress(Address a) {
		return addressRepo.save(a);
	}
}
