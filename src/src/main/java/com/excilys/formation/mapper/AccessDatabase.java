package src.main.java.com.excilys.formation.mapper;

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
	String username = "admincdb";
	String psw = "qwerty1234";
	String BDD= "";
	String url= "@127.0.0.1" + BDD;
	
	public static void main(String[] args){
		String connectionUrl = "";
		try (Connection connection = DriverManager.getConnection(connectionUrl);){
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
