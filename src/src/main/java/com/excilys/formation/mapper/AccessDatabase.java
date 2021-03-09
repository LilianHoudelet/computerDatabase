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
	String url= "jdbc://" + BDD;
	
	AccessDatabase instance;
	
	private AccessDatabase() {
		
	}
	
	public AccessDatabase getAccessDatabase() {
		if(this.instance == null) {
			return new AccessDatabase();
		}
		else {
			return this.instance;
		}
	}
	public static void main(String[] args){
		try(){
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
