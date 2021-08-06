package com.video.exceptions;

public class NoVideoIncludedException extends Exception{
	
	private static final String NO_VIDEO_INCLUDED_ERROR = "No s´ha trobat el vídeo!";
	
	public NoVideoIncludedException() {
		super(NO_VIDEO_INCLUDED_ERROR);
	}

}
