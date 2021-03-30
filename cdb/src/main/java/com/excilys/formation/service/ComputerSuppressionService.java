package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.SupprimerDatabaseDao;
import com.excilys.formation.model.Computer;

public class ComputerSuppressionService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerSuppressionService.class);
	
	static AccessDatabase instance = AccessDatabase.getInstance();
	
	public static void supprDataOrdi(Computer computer) throws Exception {
		
		try (Connection con = instance.getConnection();) {
			logger.debug("Appel suppression élément " + computer.getName() + " de la BDD");
			SupprimerDatabaseDao.computerInformations(con, computer);
			
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression");
		} 
	}
	
	public static void supprDataOrdiId(int id) throws Exception {
		
		try (Connection con = instance.getConnection();) {
			logger.debug("Appel suppression élément " + id + " de la BDD");
			SupprimerDatabaseDao.deleteComputer(con, id);
			
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression");
		} 
	}
}
