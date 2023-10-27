package com.melek.javaproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.melek.javaproject.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
