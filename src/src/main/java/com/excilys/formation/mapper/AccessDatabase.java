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
	static String BDD= "computer-database-db";
	static String url= "jdbc:mysql://localhost:3306/" + BDD;
	
	AccessDatabase instance;
	
	private AccessDatabase() {
		
	}
	
	public AccessDatabase getAccessDatabase(){
		if(this.instance == null) {
			return new AccessDatabase();
		}
		else {
			return this.instance;
		}
	}
	public static void main(String[] args) throws ClassNotFoundException{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,psw);  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
