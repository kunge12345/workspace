package edu.neu.cs5200.JDBC.entity;

import java.sql.Date;

public class Comment {
	private int id;
	private String comment;
	private Date date;
	private int userId;
	private int movieId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Comment(int id, String comment, Date date, int userId, int movieId) {
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.movieId = movieId;
	}
	public Comment() {}
	

}
