/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.user.property;

import java.util.ArrayList;

/**
 * 
 * @author prachi shah
 */
public class User {
	
	private String userName;
	private ArrayList<String> userPost;	

	public User() {       
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
	
	public ArrayList<String> getUserPost() {
		return userPost;
	}

	public void setUserPost(ArrayList<String> userPost) {
		this.userPost = userPost;
	}

}
