package org.pinakee.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pinakee.domain.Transformed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger log= LoggerFactory.getLogger(ExceptionControllerAdvice.class.getName());
	private  HttpStatus status=HttpStatus.SERVICE_UNAVAILABLE;
	
	@Autowired
	ExceptionHandlerMapping exceptionHandlerMapping;
	
	 	@ExceptionHandler(Exception.class)
	    public ResponseEntity<Transformed> exceptionHandler(Exception ex) {
	 		ResponseEntity<Transformed> responseEntity=null;
	 		Transformed error= new Transformed();
	 			error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
	 			error.setContent(ExceptionUtils.getStackTrace(ex));
	 		responseEntity=new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);  
	 		return responseEntity;
	 	}
}
