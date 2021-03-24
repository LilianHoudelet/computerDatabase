package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.UpdateDatabaseDao;
import com.excilys.formation.model.Computer;

public class UpdateDatabaseService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseService.class);
	
	public static void updateDataService(Computer computer) throws Exception {
		try (Connection newCon = AccessDatabase.getInstance();) {
			logger.debug("Mise a jour de " + computer.getName() + " dans la BDD");
			UpdateDatabaseDao.updateComputerInformations(newCon, computer);
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression dans la BDD");
			throw new Exception("Impossible de se connecter a la base de donnees Update");
		} 
	}
}
