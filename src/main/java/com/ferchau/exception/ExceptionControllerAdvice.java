package com.ferchau.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {
	
	@Autowired
	MessageSource messages;

	@ExceptionHandler(LogicException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleMyException(LogicException exception) {
		return new ErrorMessage(getMessage(exception), exception.getMessage());
	}

	@ExceptionHandler(LogicNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleMyException2(LogicNotFoundException exception) {
		return new ErrorMessage(getMessage(exception), exception.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleMyException(ConstraintViolationException exception) {
		return new ErrorMessage(getMessage(exception), exception.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleMyException(DataIntegrityViolationException exception) {
		return new ErrorMessage(getMessage(exception), exception.getCause().getMessage());
	}
	
	private String getMessage(LogicException e) {
		return messages.getMessage(e.getErrCode(), null, LocaleContextHolder.getLocale());
	}
	
	private String getMessage(ConstraintViolationException e) {
		return messages.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
	}
	
	private String getMessage(DataIntegrityViolationException e) {
		return messages.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
	}
}
