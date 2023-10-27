package com.melek.javaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melek.javaproject.models.Category;
import com.melek.javaproject.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	// CREATE
	public Category createCategory(Category c) {
		return categoryRepo.save(c);
	}
}
