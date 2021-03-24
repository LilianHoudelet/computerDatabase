package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.model.Computer;

public class UpdateDatabaseDao {
	
	public static final String REQUETE_UPDATE = "UPDATE computer SET introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseDao.class);
	
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
			stmt.setNull(3, 0);
		}
		stmt.setInt(4, ordinateur.getId()); // ordinateur.getCompany().getId());
		stmt.executeUpdate();
		logger.debug("Mise à jour de l'élément " + ordinateur.getName() + " dans la base de données");
	}
}
