package src.main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.com.excilys.formation.model.Computer;

public class SupprimerDatabaseDao {
	
	public static final String REQUETE_SUPPRIMER = "DELETE FROM computer WHERE name = ?";
	
	public static void computerInformations(Connection con, Computer ordinateur) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_SUPPRIMER);
		stmt.setString(1, ordinateur.getName());
		stmt.executeUpdate();
		
	}
}
