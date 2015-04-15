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

import edu.neu.cs5200.JDBC.entity.Cast;

public class CastManager {
DataSource ds;
	
	public CastManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/CastSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	public void createCast(Cast newCast){
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getActorId());
			statement.setInt(3, newCast.getMovieId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Cast> readAllCast(){
		List<Cast> CastList = new ArrayList<Cast>();
		String sql = "select * from cast";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast Cast = new Cast();
				Cast.setId(results.getInt("id"));
				Cast.setCharacterName(results.getString("characterName"));
				Cast.setActorId(results.getInt("actorId"));
				Cast.setMovieId(results.getInt("movieId"));
				CastList.add(Cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CastList;
	}
	
	public List<Cast> readAllCastForActor(int actorId){
		List<Cast> CastList = new ArrayList<Cast>();
		String sql = "select * from cast where actorId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,actorId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast Cast = new Cast();
				Cast.setId(results.getInt("id"));
				Cast.setCharacterName(results.getString("characterName"));
				Cast.setActorId(results.getInt("actorId"));
				Cast.setMovieId(results.getInt("movieId"));
				CastList.add(Cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CastList;
		
	}
	
	public List<Cast> readAllCastListForMovie(int movieId){
		List<Cast> CastList = new ArrayList<Cast>();
		String sql = "select * from cast where movieId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast Cast = new Cast();
				Cast.setId(results.getInt("id"));
				Cast.setCharacterName(results.getString("characterName"));
				Cast.setActorId(results.getInt("actorId"));
				Cast.setMovieId(results.getInt("movieId"));
				CastList.add(Cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CastList;
	}
	
	public Cast readCastForId(int CastId){
		Cast Cast = new Cast();
		String sql = "select * from cast where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CastId);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				Cast.setId(result.getInt("id"));
				Cast.setCharacterName(result.getString("characterName"));
				Cast.setActorId(result.getInt("actorId"));
				Cast.setMovieId(result.getInt("movieId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return Cast;
	}
	
	public void updateCast(int CastId, Cast newCast){
		String sql = "update cast set characterName=?, actorId=?, movieId=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getActorId());
			statement.setInt(3, newCast.getMovieId());
			statement.setInt(4, CastId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int deleteCast(int CastId){
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CastId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
