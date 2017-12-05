package se.xperjon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	
		//Register the driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		//Create connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/personal?user=root&password=root&useSSL=false");
		
		//Create statement
		Statement stmt = connection.createStatement();
		
		//Execute statement
		ResultSet result = stmt.executeQuery("Select * from personal");
		
		//Read result
		while(result.next()) {
			System.out.println(result.getString("namn"));
		}									
	}

}
