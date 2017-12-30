package org.pinakee.exception;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="pinakee")
public class ExceptionHandlerMapping {

	private Map<Class<? extends Throwable>,String> exceptionMapping;
	
	private Map<Class<? extends Throwable>,String> messageMapping;

	public Map<Class<? extends Throwable>, String> getMessageMapping() {
		return messageMapping;
	}

	public void setMessageMapping(Map<Class<? extends Throwable>, String> messageMapping) {
		this.messageMapping = messageMapping;
	}

	public Map<Class<? extends Throwable>, String> getExceptionMapping() {
		return exceptionMapping;
	}

	public void setExceptionMapping(Map<Class<? extends Throwable>, String> exceptionMapping) {
		this.exceptionMapping = exceptionMapping;
	}
	
}
