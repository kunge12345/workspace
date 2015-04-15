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

import edu.neu.cs5200.JDBC.entity.Actor;

public class ActorManager {
	DataSource ds;
	
	public ActorManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ActorSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	public void createActor(Actor newActor){
		String sql = "insert into Actor values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getFirstName());
			statement.setString(2, newActor.getLastName());
			statement.setDate(3, newActor.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Actor> readAllActors(){
		List<Actor> Actors = new ArrayList<Actor>();
		String sql = "select * from actor";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Actor Actor = new Actor();
				Actor.setId(results.getInt("id"));
				Actor.setFirstName(results.getString("firstName"));
				Actor.setLastName(results.getString("lastName"));
				Actor.setDateOfBirth(results.getDate("dateOfBirth"));			
				Actors.add(Actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Actors;
	}
	
	public Actor readActor(int ActorId){
		Actor Actor = new Actor();
		String sql = "select * from actor where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ActorId);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				Actor.setId(result.getInt("id"));
				Actor.setFirstName(result.getString("firstName"));
				Actor.setLastName(result.getString("lastName"));
				Actor.setDateOfBirth(result.getDate("dateOfBirth"));		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return Actor;
	}
	
	public void updateActor(int ActorId, Actor Actor){
		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Actor.getFirstName());
			statement.setString(2, Actor.getLastName());
			statement.setDate(3, Actor.getDateOfBirth());
			statement.setInt(4, Actor.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int deleteActor(int ActorId){
		String sql = "delete from actor where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ActorId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
				


}
