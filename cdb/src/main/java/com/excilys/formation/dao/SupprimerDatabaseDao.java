package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SupprimerDatabaseDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(SupprimerDatabaseDao.class);
	
	public static final String REQUETE_SUPPRIMER = "DELETE FROM computer WHERE name = ?";
	
	public static final String REQUETE_SUPPRIMER_PAR_ID = "DELETE FROM computer WHERE id = ?";
	
	public void computerInformations(Connection con, Computer ordinateur) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_SUPPRIMER);
		stmt.setString(1, ordinateur.getName());
		stmt.executeUpdate();
		logger.debug("Suppression de l'élément " + ordinateur.getName() + " de la base de données");
	}
	
	public void deleteComputer(Connection con, int id) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_SUPPRIMER_PAR_ID);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		logger.debug("Suppression de l'élément " + id + " de la base de données");
	}
}
