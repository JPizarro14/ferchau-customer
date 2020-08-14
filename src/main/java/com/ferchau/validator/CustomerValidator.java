package com.ferchau.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.ferchau.errors.ErrorCodes;
import com.ferchau.exception.LogicException;
import com.ferchau.model.bbdd.Customer;

@Component
public class CustomerValidator {
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final String NICKNAME = "Nickname";
	private static final String NAME = "Name";
	private static final String SURNAME = "Surname";
	private static final String EMAIL = "Email";
	
	public void checkEmail(String email) throws LogicException {
		checkFieldNull(email, EMAIL);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
        	throw new LogicException("error.customer.validation", "Invalid email");
        }
	}
	
	public void checkFieldNull(String value, String field) throws LogicException {
		if(value == null || value.isEmpty()) {
			throw new LogicException(ErrorCodes.ERROR_VALIDATION, String.format(ErrorCodes.ERROR_EMPTY_MSG, field));
		}
		
	}
	
	public void checkCustomer(Customer cust) throws LogicException {
		checkEmail(cust.getEmail());
		checkFieldNull(cust.getNickname(), NICKNAME);
		checkFieldNull(cust.getName(), NAME);
		checkFieldNull(cust.getSurname(), SURNAME);
	}
	
}
