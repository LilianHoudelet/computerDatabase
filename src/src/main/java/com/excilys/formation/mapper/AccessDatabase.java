package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	static String BDD = "computer-database-db";
	static String url = "jdbc:mysql://localhost:3306/" + BDD;
	
	private static Connection instance;
	
	/**
	 * ici, une connextion a la base de donnee est cree
	 * @throws ClassNotFoundException
	 */
	private AccessDatabase() throws ClassNotFoundException {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			instance = DriverManager.getConnection(url,username,psw);  
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance() throws ClassNotFoundException{
		if(instance == null) {
			new AccessDatabase();
		}
		return instance;
	}
}
