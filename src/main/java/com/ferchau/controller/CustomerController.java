package com.ferchau.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferchau.exception.LogicException;
import com.ferchau.model.bbdd.Customer;
import com.ferchau.model.customer.request.CustomerRequest;
import com.ferchau.model.customer.response.CustomerResponse;
import com.ferchau.service.CustomerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/${rest.api.version}")
@Api(value = "Customer API", description = "Customer Signature operations")
public class CustomerController {

	private static Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<CustomerResponse> getCustomers(@RequestParam(value="nickname", required=false) String nickname) throws LogicException {
		log.debug("getCustomers()");
		List<Customer> lsCustomer = customerService.list(nickname);
		List<CustomerResponse> lsResponse = lsCustomer.stream().map((x) -> new CustomerResponse(x)).collect(Collectors.toList());
		return lsResponse;
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long id) throws LogicException {
		log.debug("getCustomer() --getting customer id: " + id);
		Customer customer = customerService.get(id);
		CustomerResponse response = new CustomerResponse(customer);
		log.debug("Customer id " + id + " found successfully!");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/customers")
	public ResponseEntity<Long> createCustomer(@RequestBody CustomerRequest request) throws LogicException {
		log.debug(">> createCustomer");
		log.debug("Creating customer: " + request.getName() + " " + request.getSurname());
		Long id = customerService.save(request);
		log.debug("<< createCustomer finished successfully!");
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) throws LogicException {
		log.debug(">> deleteCustomer");
		log.debug("Deleting customer: " + id);
		customerService.delete(id);
		log.debug("<< deleteCustomer finished successfully!");
		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Long> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) throws LogicException {
		log.debug(">> updateCustomer");
		log.debug("Updating customer: " + id + " " + request.getName() + " " + request.getSurname());
		customerService.update(request, id);
		log.debug("<< updateCustomer finished successfully!");
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}


}