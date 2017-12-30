package org.pinakee.exception;

import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pinakee.domain.Transformed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ExceptionHandlerMapping exceptionHandlerMapping;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Transformed> exceptionHandler(Exception ex) {
		log.error("Exception occured and it is captured and sent back in the response : \n {} ", ex);
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		Transformed error = new Transformed();
		error.setStatus(status.value());
		error.setContent(ExceptionUtils.getMessage(ex));
		
		Class<? extends Throwable> clazz = ex.getClass();
		Map<Class<? extends Throwable>, String> exceptionMapping = exceptionHandlerMapping.getExceptionMapping();
		Map<Class<? extends Throwable>, String> messageMapping = exceptionHandlerMapping.getMessageMapping();
		
		String strVal = exceptionMapping.get(clazz);
		String message=messageMapping.get(clazz);
		
		if (null != strVal) {
			Integer sts = Integer.valueOf(strVal);
			error.setStatus(HttpStatus.valueOf(sts).value());
			status = HttpStatus.valueOf(sts);
		}
		if (null != message) {
			error.setContent(message);
		}
		return new ResponseEntity<Transformed>(error, status);
	}
}
