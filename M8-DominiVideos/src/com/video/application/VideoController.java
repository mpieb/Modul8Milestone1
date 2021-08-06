package com.video.application;

import java.util.*;

import javax.swing.JOptionPane;

import com.video.domain.*;
import com.video.exceptions.NoVideoIncludedException;
import com.video.persistance.VideoRepository;

public class VideoController {

	private VideoRepository repository;

	public VideoController(){
		
		repository = new VideoRepository();
	
	}
	
	public void createVideo(String url, String tittle, User owner, List<Tag> tags) throws NoVideoIncludedException {
		Video video = new Video(url, tittle, owner, tags);
		repository.addVideo(video);
	}
	
	public String getAllVideos() {
		
		return repository.getAllVideos().toString();
	
	}
	
	public List<Video> getRepository() {
		
		return repository.getAllVideos();
	
	}
	
	public List<Tag> addTag(int amountOfTags) {
		
		List<Tag> tagList = new ArrayList<>();
		
		for(int i=0;i<amountOfTags;i++) {
			
			String strInput = JOptionPane.showInputDialog(null, "Escriu el t�tol del TAG n�: " + (i+1) + " [" + i + "/"+amountOfTags, "Escriu el t�tol del TAG",JOptionPane.QUESTION_MESSAGE);
			
			if(strInput!=null) {
				tagList.add(new Tag(strInput));
			}
		}
		
		return tagList;
	}
	
	public List<Video> getUserVideoList(User user){
		
		List<Video> videosForUser = new ArrayList<>();
		
		for(Video v: repository.getAllVideos()) {
			if(v.getOwner().getUserId() == user.getUserId()) videosForUser.add(v);
		}
		
		return videosForUser;
	}
	
}

   




