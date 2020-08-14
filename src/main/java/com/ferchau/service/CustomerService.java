package com.ferchau.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ferchau.exception.LogicException;
import com.ferchau.model.bbdd.Customer;
import com.ferchau.model.customer.request.CustomerRequest;

public interface CustomerService {
	List<Customer> list(String nickname) throws LogicException;

	Customer get(Long id) throws LogicException;

	Long save(CustomerRequest request) throws LogicException;

	void update(CustomerRequest request, Long id) throws LogicException;

	void delete(Long id) throws LogicException;
}
