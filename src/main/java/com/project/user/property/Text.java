package com.project.user.property;

import java.util.ArrayList;

import com.project.user.property.Comment;


/**
 * 
 * @author prachi shah
 */

public class Text {
			
	public int userTxtId;
	public String userPost;	
	public ArrayList<Comment>  comments;

	public Text() {

	}
	
			
	public int getUserTxtId() {
		return userTxtId;
	}


	public void setUserTxtId(int userTxtId) {
		this.userTxtId = userTxtId;
	}


	/**
	 * @return
	 */
	public String getUserPost() {
		return userPost;
	}
	
	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}	
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	

}
