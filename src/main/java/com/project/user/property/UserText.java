package com.project.user.property;

import java.util.ArrayList;

/**
 * 
 * @author prachi shah
 */
public class UserText {
	
	private int userId;	
	private String userName;	
	private ArrayList<Text> userPost;	

	public UserText() {       
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
		
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public ArrayList<Text> getUserPost() {
		return userPost;
	}

	public void setUserPost(ArrayList<Text> userPost) {
		this.userPost = userPost;
	}

}
