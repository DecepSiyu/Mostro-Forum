package com.postBean;

import java.sql.Date;

public class Comment {
	private String CommentID;
	private String postID;
	private Date publishTime;
	private String contents;
	private String auther;
	
	public Comment(String commentID, String postID, Date publishTime, String contents, String auther) {
		super();
		CommentID = commentID;
		this.postID = postID;
		this.publishTime = publishTime;
		this.contents = contents;
		this.auther = auther;
	}
	public String getCommentID() {
		return CommentID;
	}
	public void setCommentID(String commentID) {
		CommentID = commentID;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}

}
