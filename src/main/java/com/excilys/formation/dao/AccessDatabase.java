package main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Here we are connecting to the database, the informations given are used in the connectionUrl variable
 * and used to access the database
 * 
 * @author Lilian Houdelet
 *
 */
public class AccessDatabase {
	static String username = "admincdb";
	static String psw = "qwerty1234";
	static String dataBase = "computer-database-db";
	static String url = "jdbc:mysql://localhost:3306/" + dataBase;
	
	private static Connection instance;
	
	/**
	 * ici, une connextion a la base de donnee est cree
	 * @throws ClassNotFoundException
	 */
	private AccessDatabase() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			instance = DriverManager.getConnection(url, username, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null || instance.isClosed()) {
			new AccessDatabase();
		}
		return instance;
	}
}
