package com.melek.javaproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.melek.javaproject.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
