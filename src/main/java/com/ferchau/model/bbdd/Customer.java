package com.ferchau.model.bbdd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ferchau.model.customer.request.CustomerRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMERS")
@ApiModel(description = "Class representing a Customer")
public class Customer {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "customerId", required = true)
	@NotNull
	private long customerId;

	@Column(name = "NICKNAME", nullable = false, unique = true)
	@ApiModelProperty(notes = "Customer Nickname", required = true)
	@NotNull
	private String nickname;

	@Column(name = "NAME", nullable = false)
	@ApiModelProperty(notes = "Customer first name", required = true)
	@NotNull
	private String name;

	@Column(name = "SURNAME", nullable = false)
	@ApiModelProperty(notes = "Customer surname", required = true)
	@NotNull
	private String surname;

	@Column(name = "EMAIL", nullable = false, unique = true)
	@ApiModelProperty(notes = "Customer Email", required = true)
	@NotNull
	@Email
	private String email;

	@Transient
	private static final Logger log = LoggerFactory.getLogger(Customer.class);

	public Customer() {
		super();
	}

	public Customer(long customerId, String nickname, String name, String surname, String email) {
		super();
		this.customerId = customerId;
		this.nickname = nickname;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public Customer(CustomerRequest request) {
		super();
		this.email = request.getEmail();
		this.name = request.getName();
		this.nickname = request.getNickname();
		this.surname = request.getSurname();
		this.nickname = request.getNickname();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFieldsByRequest(CustomerRequest request) {
		if (request.getEmail() != null && !request.getEmail().isEmpty()) {
			this.email = request.getEmail();
		}
		if (request.getName() != null && !request.getName().isEmpty()) {
			this.name = request.getName();
		}
		if (request.getSurname() != null && !request.getSurname().isEmpty()) {
			this.surname = request.getSurname();
		}
	}
	
}
