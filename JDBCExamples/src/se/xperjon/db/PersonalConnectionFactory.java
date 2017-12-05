package se.xperjon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersonalConnectionFactory implements ConnectionFactory{

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception ex) {
			System.out.println("unable to load mysql jdbc driver: " + ex.getMessage());
		}
	}

	@Override
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/personal?user=root&password=root&useSSL=false");
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		return null;
	}
}
