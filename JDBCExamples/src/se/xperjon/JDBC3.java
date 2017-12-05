package se.xperjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC3 {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// Create connection
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/northwind?user=root&password=root&useSSL=false");

			// Execute statement
			String customerID = "VICTE";
			stmt = connection.prepareStatement(
					"select c.CustomerID as cid, o.OrderID as oid, o.OrderDate as odate, round(sum(od.Quantity * od.UnitPrice*(1-od.Discount)),1) as totalPrice from "
							+ "customers c inner join (`order details` od, orders o) on c.CustomerID = o.CustomerID and o.OrderID = od.OrderID "
							+ "where c.CustomerID = ? group by o.OrderID;");

			stmt.setString(1, customerID);
			rs = stmt.executeQuery();

			// Read result
			System.out.println("------------ orders for customer = " + customerID + "-----------------");
			while (rs.next()) {
				System.out.println(
						rs.getInt("oid") + " " + rs.getDate("odate") + " " + rs.getDouble("totalPrice") + " SEK");
			}


		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}

}
