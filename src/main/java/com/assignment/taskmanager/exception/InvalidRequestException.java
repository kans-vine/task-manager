package com.assignment.taskmanager.exception;

/**
 * A custom exception class to validate the requests for task manager.
 * 
 * @author kans.samy
 */
public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException() {
		
	}

	public InvalidRequestException(String msg) {
		super(msg);
	}

}
