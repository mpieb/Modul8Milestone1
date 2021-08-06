package com.video.exceptions;

public class NoVideoFoundException extends Exception {

private static final String NO_VIDEO_FOUND_ERROR = "No s´ha trobat un vídeo amb aquest identificador!";
	
	public NoVideoFoundException() {
		super(NO_VIDEO_FOUND_ERROR);
	}

}
