package se.xperjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC2 {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// Unable to register driver!
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//Create connection
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/personal?user=root&password=root&useSSL=false");
			// Create statement
			stmt = connection.createStatement();

			// Execute statement
			rs = stmt.executeQuery("Select * from personal");

			// Read result
			while (rs.next()) {
				System.out.println(rs.getString("namn"));
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}

	}

}
