package com.melek.javaproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.melek.javaproject.models.Charity;


@Repository
public interface CharityRepository extends CrudRepository<Charity, Long> {

	List<Charity> findAll();
}
