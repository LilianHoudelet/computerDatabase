package main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.excilys.formation.dao.AccessDatabase;

public class SupprDatabase {
	
public static final String REQUETE = "DELETE FROM computer WHERE name = \"";
	
	public static void supprimerInfos(String nomMachine) throws ClassNotFoundException, SQLException {
		
		try (Connection con = AccessDatabase.getInstance();) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(REQUETE + nomMachine + "\"");  
						
			con.close();
		} catch (SQLException e) {
			
		}
	}	
}
