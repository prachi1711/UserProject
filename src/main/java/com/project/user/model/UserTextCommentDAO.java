package com.project.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.user.property.Comment;
import com.project.user.model.ConnectionDAO;
import com.project.user.property.Text;
import com.project.user.property.UserText;
import com.project.user.property.User;


public class UserTextCommentDAO
{
	public static Comment insert(Comment comment) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		int id;
		ConnectionDAO conn= new ConnectionDAO();
		String query = " insert into user_text_comment (user_text_id, comment)"
                + " values (?, ?)";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);		
		preparedStmt.setInt (1, comment.getUserTxtId());
		preparedStmt.setString (2, comment.getComment());
		// execute the preparedstatement
		preparedStmt.execute();
		ResultSet rs = preparedStmt.getGeneratedKeys();
	    rs.next();
	    id = rs.getInt(1);
	    comment.setCommentId(id);
		conn.closeConnection();
		return comment;
	}
	
	public static UserText select(String userName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ConnectionDAO conn= new ConnectionDAO();
		UserText userTxt = new UserText();		
		ArrayList<Text> posts = null;
		Text txt = null;
		Comment c1 = null;
		ArrayList<Comment> commentList = null;
		int i;	
		boolean found = false;
						
		String query = "select  b.id, b.userText, a.comment, c.username "
				+ " from user_text b left outer join user_text_comment a"
				+ " on a.user_text_id = b.id" 
				+ " inner join user c on b.user_id = c.id" 
				+ " where c.username =  ? ";	                
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);
		preparedStmt.setString(1, userName);
		// execute the preparedstatement
		userTxt.setUserName(userName);
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			found = false;
			if(posts != null && posts.size() > 0) {				
				for (i = 0; i < posts.size(); i++) {
					txt = posts.get(i);
					if(txt != null) {
						if (txt.getUserTxtId() == rs.getInt("id")) {
							commentList = txt.getComments();
							if(rs.getString("comment")!= null && !rs.getString("comment").equalsIgnoreCase("")) {
								c1 = new Comment();
								c1.setUserName(userName);
								c1.setUserTxtId(rs.getInt("id"));
								c1.setComment(rs.getString("comment"));
								if (commentList != null) {
									commentList.add(c1);
									txt.setComments(commentList);
								} else {
									commentList = new ArrayList<Comment>();
									commentList.add(c1);
									txt.setComments(commentList);	
								}								
							}
							found = true;
							break;
						} 
					}
				}
			} else {
				posts = new ArrayList<Text>();
			}
			if(!found) {
				if(rs.getString("userText")!= null && !rs.getString("userText").equalsIgnoreCase("")) {
					txt = new Text();
					txt.setUserTxtId(rs.getInt("id"));
					txt.setUserPost(rs.getString("userText"));
					if(rs.getString("comment")!= null && !rs.getString("comment").equalsIgnoreCase("")) {
						commentList = new ArrayList<Comment>();
						c1 = new Comment();
						c1.setUserName(userName);
						c1.setUserTxtId(rs.getInt("id"));
						c1.setComment(rs.getString("comment"));
						commentList.add(c1);
						txt.setComments(commentList);							
					}	
					posts.add(txt);	
				}
			}														    
		}		
		userTxt.setUserPost(posts);				
		conn.closeConnection();
		return userTxt;
	}  
}