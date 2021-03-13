package src.main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import src.main.java.com.excilys.formation.model.Computer;

public class AjoutOrdinateurDao {
public static final String REQUETE_AJOUTER_COMPLET = "INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?)";
	
	public static void computerInformations(Connection con, Computer ordinateur) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_AJOUTER_COMPLET);
		stmt.setInt(1, ordinateur.getId());
		stmt.setString(2, ordinateur.getName());
		if (ordinateur.getDateSortie() != null) {
			stmt.setDate(3, Date.valueOf(ordinateur.getDateSortie()));
		} else {
			stmt.setDate(3, null);
		}
		if (ordinateur.getDateRetrait() != null) {
			
			stmt.setDate(4, Date.valueOf(ordinateur.getDateRetrait()));
		} else {
			stmt.setDate(4, null);
		}
		stmt.setInt(5, ordinateur.getCompany().getId()); // ordinateur.getCompany().getId());
		stmt.executeUpdate();
	}
}
