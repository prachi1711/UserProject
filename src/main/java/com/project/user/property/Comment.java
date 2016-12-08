/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.user.property;

/**
 * 
 * @author prachi shah
 */
public class Comment {
			
	public int commentId;
	public int userTxtId;
	public String comment;
	public String userName;

	public Comment() {

	}
	
	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public int getUserTxtId() {
		return userTxtId;
	}

	public void setUserTxtId(int userTxtId) {
		this.userTxtId = userTxtId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
			
	
}
