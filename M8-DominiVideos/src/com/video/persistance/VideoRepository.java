package com.video.persistance;

import java.util.*;
import com.video.domain.Video;
import com.video.exceptions.NoVideoFoundException;
import com.video.exceptions.NoVideoIncludedException;

public class VideoRepository {
	
	private static List<Video> videos = new ArrayList<>();
	
	public VideoRepository() {;
	}
	
	public List<Video> getAllVideos(){
		return new ArrayList<>(videos);
	}
	
	
	public void addVideo(Video video) throws NoVideoIncludedException{
		
		if(video==null) {
			System.err.println("El vídeo no pot ser nul, per poder afegir-lo a la llista de membres!");
			throw new NoVideoIncludedException();
		}
		videos.add(video);
	
	}
	
	public Video getVideoById(int idVideo) throws NoVideoFoundException {
		
		for(Video video: videos) {
			if(video.getVideoId()==idVideo) return video;
		}
		throw new NoVideoFoundException();
	
	}

}

    

