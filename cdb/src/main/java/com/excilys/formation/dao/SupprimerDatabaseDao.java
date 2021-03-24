package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.model.Computer;

public class SupprimerDatabaseDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(SupprimerDatabaseDao.class);
	
	public static final String REQUETE_SUPPRIMER = "DELETE FROM computer WHERE name = ?";
	
	public static void computerInformations(Connection con, Computer ordinateur) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_SUPPRIMER);
		stmt.setString(1, ordinateur.getName());
		stmt.executeUpdate();
		logger.debug("Suppression de l'élément " + ordinateur.getName() + " de la base de données");
	}
}
