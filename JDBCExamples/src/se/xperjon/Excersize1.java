package se.xperjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Excersize1 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// Create connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/northwind?user=root&password=root&useSSL=false");

			// Execute statement
			stmt = conn.prepareStatement("SELECT p.ProductID, p.ProductName,c.CategoryName,p.UnitPrice,p.UnitsInStock "
					+ "FROM products p INNER JOIN categories c ON p.CategoryID = c.CategoryID "
					+ "WHERE unitprice < ? AND UnitsInStock >= ? AND ProductName LIKE CONCAT('%',?,'%') AND c.CategoryName = ?;");

			stmt.setInt(1, 30);
			stmt.setInt(2, 75);
			stmt.setString(3, "b");
			stmt.setString(4, "Condiments");

			rs = stmt.executeQuery();
			// Read result
			while (rs.next()) {
				System.out.println(
						rs.getInt("ProductID") + " " + rs.getString("ProductName") + " " + rs.getString("CategoryName")
								+ " " + rs.getInt("UnitPrice") + " SEK " + rs.getInt("UnitsInStock") + " st");
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

	}

}
