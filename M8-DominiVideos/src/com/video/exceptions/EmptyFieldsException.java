package com.video.exceptions;

public class EmptyFieldsException extends Exception{
	
	private static final String EMPTY_FIELD_ERROR = "Alguns camps són buits!";
	
	public EmptyFieldsException() {
		super(EMPTY_FIELD_ERROR);
	}

}
