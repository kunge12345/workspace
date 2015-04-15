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

import edu.neu.cs5200.JDBC.entity.Movie;

public class MovieManager {

	DataSource ds;
	
	public MovieManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	public void createMovie(Movie newMovie){
		String sql = "insert into movie values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getTitle());
			statement.setString(2, newMovie.getPosterImage());
			statement.setDate(3, newMovie.getReleaseDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Movie> readAllMovies(){
		List<Movie> Movies = new ArrayList<Movie>();
		String sql = "select * from movie";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Movie Movie = new Movie();
				Movie.setId(results.getInt("id"));
				Movie.setTitle(results.getString("title"));
				Movie.setPosterImage(results.getString("posterImage"));
				Movie.setReleaseDate(results.getDate("releaseDate"));			
				Movies.add(Movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Movies;
	}
	
	public Movie readMovie(int MovieId){
		Movie Movie = new Movie();
		String sql = "select * from movie where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, MovieId);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				Movie.setId(result.getInt("id"));
				Movie.setTitle(result.getString("title"));
				Movie.setPosterImage(result.getString("posterImage"));
				Movie.setReleaseDate(result.getDate("releaseDate"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return Movie;
	}
	
	public void updateMovie(int MovieId, Movie Movie){
		String sql = "update movie set title=?, posterImage=?, releaseDate=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Movie.getTitle());
			statement.setString(2, Movie.getPosterImage());
			statement.setDate(3, Movie.getReleaseDate());
			statement.setInt(4, Movie.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int deleteMovie(int MovieId){
		String sql = "delete from movie where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, MovieId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
				

}
