package com.ferchau.model.customer.response;

import com.ferchau.model.bbdd.Customer;

public class CustomerResponse {

	private long id;
	private String name;
	private String surname;
	private String email;
	private String nickname;

	public CustomerResponse() {
		super();
	}

	public CustomerResponse(long id, String name, String surname, String email, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.nickname = nickname;
	}
	
	public CustomerResponse(Customer customer) {
		super();
		this.id = customer.getCustomerId();
		this.name = customer.getName();
		this.surname = customer.getSurname();
		this.email = customer.getEmail();
		this.nickname = customer.getNickname();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
