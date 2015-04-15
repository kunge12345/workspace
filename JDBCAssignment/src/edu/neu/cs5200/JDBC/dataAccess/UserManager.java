package edu.neu.cs5200.JDBC.dataAccess;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import edu.neu.cs5200.JDBC.entity.*;

public class UserManager implements DataSource{
	Connection conn = null;
	String url = "jdbc:mysql://localhost/cs5200";
	String username = "root";
	String password = "";

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		try {
            DriverManager.setLogWriter(new PrintWriter(
                            "./Logfile.log"));
    } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
