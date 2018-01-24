package com.postBean;

import java.sql.Date;
import java.util.ArrayList;

public class Post {
	private String postID;
	private Date publishTime;
	private String contents;
	private String auther;
	private String title;
	private Plate plate;

	private ArrayList<Comment> comments;

	public Post(String postID, String title, Date publishTime, String auther, String contents) {
		this.postID = postID;
		this.contents = contents;
		this.publishTime = publishTime;
		this.auther = auther;
		this.title = title;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getContent() {
		return contents;
	}

	public String getBriefContent() {
		if (contents.length() > 100) {
			return contents.substring(0, 100) + "...";
		} else {
			return contents;
		}
	}

	public void setContent(String contents) {
		this.contents = contents;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}
}
