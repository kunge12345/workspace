package edu.neu.cs5200.JDBC.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.JDBC.entity.Comment;

public class CommentManager {
	DataSource ds;
	
	public CommentManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/CommentSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	public void createComment(Comment newComment){
		String sql = "insert into comment values (null,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getComment());
			statement.setDate(2, newComment.getDate());
			statement.setInt(3, newComment.getUserId());
			statement.setInt(4, newComment.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Comment> readAllComments(){
		List<Comment> Comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment Comment = new Comment();
				Comment.setId(results.getInt("id"));
				Comment.setComment(results.getString("comment"));
				Comment.setDate(results.getDate("date"));
				Comment.setUserId(results.getInt("userId"));
				Comment.setMovieId(results.getInt("movieId"));
				Comments.add(Comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Comments;
	}
	
	public List<Comment> readAllCommentsForUsername(String username){
		List<Comment> Comments = new ArrayList<Comment>();
		String sql = "select c.* from comment c,user u where c.userId=u.uId and u.username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment Comment = new Comment();
				Comment.setId(results.getInt("id"));
				Comment.setComment(results.getString("comment"));
				Comment.setDate(results.getDate("date"));
				Comment.setUserId(results.getInt("userId"));
				Comment.setMovieId(results.getInt("movieId"));
				Comments.add(Comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Comments;
		
	}
	
	public List<Comment> readAllCommentsForMovie(int movieId){
		List<Comment> Comments = new ArrayList<Comment>();
		String sql = "select * from comment where movieId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment Comment = new Comment();
				Comment.setId(results.getInt("id"));
				Comment.setComment(results.getString("comment"));
				Comment.setDate(results.getDate("date"));
				Comment.setUserId(results.getInt("userId"));
				Comment.setMovieId(results.getInt("movieId"));
				Comments.add(Comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Comments;
	}
	
	public Comment readCommentForId(int CommentId){
		Comment Comment = new Comment();
		String sql = "select * from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CommentId);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				Comment.setId(result.getInt("id"));
				Comment.setComment(result.getString("comment"));
				Comment.setDate(result.getDate("date"));
				Comment.setUserId(result.getInt("userId"));
				Comment.setMovieId(result.getInt("movieId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return Comment;
	}
	
	public void updateComment(int CommentId, String newComment){
		String sql = "update comment set comment=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setInt(2, CommentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int deleteComment(int CommentId){
		String sql = "delete from Comment where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CommentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
				



}
