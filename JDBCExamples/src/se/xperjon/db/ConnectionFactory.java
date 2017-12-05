package se.xperjon.db;

import java.sql.Connection;

public interface ConnectionFactory {

	public Connection getConnection();
	
}
