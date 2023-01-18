package com.assignment.taskmanager.exception;

import java.util.Date;
import java.util.List;

/**
 * Global Error Response for exception messages
 * 
 * @author kans.samy
 */
public class ErrorResponse {

	private int status;
	private List<String> errors;
	private Date timestamp;

	public ErrorResponse() {
	}

	public ErrorResponse(List<String> message) {
		super();
		this.errors = message;
	}

	public ErrorResponse(int statusCode, List<String> message) {
		super();
		this.status = statusCode;
		this.errors = message;
	}

	public ErrorResponse(int statusCode, List<String> message, Date timestamp) {
		super();
		this.status = statusCode;
		this.errors = message;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
