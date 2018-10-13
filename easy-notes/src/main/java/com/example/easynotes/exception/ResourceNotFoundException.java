/**
 * 
 */
package com.example.easynotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author e1077874
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9153386366344360716L;
	
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	/**
	 * 
	 */
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue() {
		return fieldValue;
	}
}
