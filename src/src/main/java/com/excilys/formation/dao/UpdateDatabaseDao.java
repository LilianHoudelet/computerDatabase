package src.main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.com.excilys.formation.model.Computer;

public class UpdateDatabaseDao {
	
	public static final String REQUETE_UPDATE = "UPDATE computer SET introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	
	public static void updateComputerInformations(Connection con, Computer ordinateur) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_UPDATE);
		
		if (ordinateur.getDateSortie() != null) {
			stmt.setDate(1, Date.valueOf(ordinateur.getDateSortie()));
		} else {
			stmt.setDate(1, null);
		}
		if (ordinateur.getDateRetrait() != null) {
			stmt.setDate(2, Date.valueOf(ordinateur.getDateRetrait()));
		} else {
			stmt.setDate(2, null);
		}
		if (ordinateur.getCompany().getId() != 0) {
			stmt.setInt(3, ordinateur.getCompany().getId());
		} else {
			stmt.setDate(3, null);
		}
		stmt.setInt(4, ordinateur.getId()); // ordinateur.getCompany().getId());
		stmt.executeUpdate();
	}
}
