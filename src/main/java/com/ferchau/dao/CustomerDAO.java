package com.ferchau.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ferchau.model.bbdd.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long> {

	Customer findByNickname(String nickname);
	Customer findByEmail(String email);
}
