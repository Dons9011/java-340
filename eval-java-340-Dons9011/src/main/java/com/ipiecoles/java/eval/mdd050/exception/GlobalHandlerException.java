package com.ipiecoles.java.eval.mdd050.exception;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(
		EntityNotFoundException entityNotFoundException) {
		return entityNotFoundException.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException  e) {
		return "la valeur '" + e.getValue() + "' est incorrect pour le param '" +e.getName() + "'";
	}
	
	@ExceptionHandler (EntityExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleDuplicateNameException(
			EntityExistsException  e) {
		return e.getMessage();
	}
	
	
}
