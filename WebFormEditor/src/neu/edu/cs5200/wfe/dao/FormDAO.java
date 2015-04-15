package neu.edu.cs5200.wfe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormDAO {

	public void create(String formName) {
		Connection conn = getConnection();
		String sql = "insert into form values (?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, formName);
			stmt.executeUpdate();
			closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readAll() {

	}

	public void readOne() {

	}

	public void update() {

	}

	public void remove() {

	}

	public Connection getConnection() {
		Connection connect = null;
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/cs5200?"
							+ "user=root&password=Incredible@716");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FormDAO dao = new FormDAO();
		dao.create();

	}

}
