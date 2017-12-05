package se.xperjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankTransApp {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		try {
			// Create connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bank?user=root&password=root&useSSL=false");
			conn.setAutoCommit(false);

			// Prepare statements
			stmt1 = conn.prepareStatement("SELECT amount from accounts a where a.number = ?");
			stmt2 = conn.prepareStatement("UPDATE accounts a set a.amount = a.amount-100 where a.number = ?");
			stmt3 = conn.prepareStatement("UPDATE accounts a set a.amount = a.amount+100 where a.number = ?");
			stmt1.setInt(1, 1);
			stmt2.setInt(1, 1);
			stmt3.setInt(1, 2);

			// Execute statements
			rs = stmt1.executeQuery();
			System.out.println("Reading amount from account: 1");
			Integer amountOnAccount1 = null;
			while (rs.next()) {
				amountOnAccount1 = rs.getInt("amount");
				System.out.println("Amount on account 1 is: "+amountOnAccount1);
			}
			if (amountOnAccount1 != null && amountOnAccount1 >= 100) {
				System.out.println("Making withdrawal from account 1");
				stmt2.executeUpdate();
				System.out.println("Making deposit on account 2");
				stmt3.executeUpdate();
			}
			conn.commit();

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			if (conn != null) {
				System.out.println("Transaction is being rolled back!");
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt1.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt2.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt3.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
	}
}
