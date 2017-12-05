package se.xperjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import se.xperjon.model.Personal;

public class Main {

	public static void main(String[] args) throws SQLException {

		// Create connection
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/personal?user=root&password=root&useSSL=false");

		BeanHandler<Personal> h = new BeanHandler<Personal>(Personal.class);
		QueryRunner runner = new QueryRunner();
		Personal personal = runner.query(connection, "Select * from personal p where p.personalID = 7313", h);
		System.out.println(personal);

	}

}
