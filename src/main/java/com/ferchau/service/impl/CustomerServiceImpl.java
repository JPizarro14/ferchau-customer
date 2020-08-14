package com.ferchau.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferchau.dao.CustomerDAO;
import com.ferchau.exception.LogicException;
import com.ferchau.exception.LogicNotFoundException;
import com.ferchau.model.bbdd.Customer;
import com.ferchau.model.customer.request.CustomerRequest;
import com.ferchau.service.CustomerService;
import com.ferchau.validator.CustomerValidator;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerValidator customerValidator;
	
	@Transactional(readOnly = true)
	public List<Customer> list(String nickname) throws LogicException {
		Iterable<Customer> iterable = null;
		List<Customer> lsResponse = new ArrayList<Customer>();
		try {
			if (nickname == null || nickname.isEmpty()) {
				iterable = customerDAO.findAll();
				iterable.forEach(lsResponse::add);
			} else {
				Customer cust = customerDAO.findByNickname(nickname);
				lsResponse.add(cust);
			}
		} catch (Exception e) {
			log.error("-- error list() customer " + e.getMessage());
			throw new LogicException("customer.error.list", e.getMessage());
		}
		return lsResponse;
	}

	@Transactional(readOnly = true)
	public Customer get(Long id) throws LogicException {
		log.debug(">> get Customer: " + id);
		Customer customer = null;
		try {
			customer = customerDAO.findById(id).get();

			if (customer != null) {
				log.debug("<< get Customer: " + customer.getCustomerId() + " " + customer.getName() + " "
						+ customer.getSurname());
			} else {
				throw new LogicNotFoundException("customer.error.not.found", String.valueOf(id));
			}
			
		} catch (LogicNotFoundException e) {
			log.error("-- error get() customer " + e.getMessage());
			throw new LogicNotFoundException("customer.error.not.found", e.getMessage());
			
		} catch (Exception e) {
			log.error("-- error get() customer " + e.getMessage());
			throw new LogicException("customer.error.get", e.getMessage());
		}

		return customer;
	}
	
	@Transactional
	public Long save(CustomerRequest request) throws LogicException, ConstraintViolationException {
		log.debug(">> save Customer: " + request.getName() + " " + request.getSurname());
		Long response = null;
		try {
			Customer customer = new Customer(request);
			customerValidator.checkCustomer(customer);
			if(customerDAO.findByEmail(customer.getEmail()) != null) {
				throw new LogicException("customer.error.save", "Email registered");
			}
			customerDAO.save(customer);		
			response = new Long(customer.getCustomerId());
		} catch (Exception e) {
			log.error("-- error save() customer " + e.getMessage());
			throw new LogicException("customer.error.save", e.getMessage());
		}
		log.debug("<< save Customer");
		return response;
	}

	@Transactional
	public void update(CustomerRequest request, Long id) throws LogicException {
		log.debug(">> update Customer: " + request.getName() + " " + request.getSurname());
		try {
			Customer customer = customerDAO.findById(id).get();
			customer.setFieldsByRequest(request);
			
			customerDAO.save(customer);
			
		} catch (Exception e) {
			log.error("-- error update() customer " + e.getMessage());
			throw new LogicException("customer.error.update", e.getMessage());
		}
		log.debug("<< update Customer");
	}

	@Transactional
	public void delete(Long id) throws LogicException {
		log.debug(">> delete Customer: " + id);
		try {
			Customer customer = customerDAO.findById(id).get();
			if(customer != null) {
				customerDAO.deleteById(id);
			} else {
				throw new LogicNotFoundException("customer.error.not.found", String.valueOf(id));
			}
		} catch (Exception e) {
			log.error("-- error delete() customer " + e.getMessage());
			throw new LogicException("customer.error.delete", e.getMessage());
		}
		log.debug("<< delete Customer");
	}
	
}
