package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.AjoutOrdinateurDao;
import com.excilys.formation.model.Computer;

public class AjoutOrdinateurService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurService.class);
	
	public static void ajoutDataService(Computer computer) throws Exception {
		try (Connection newCon = AccessDatabase.getInstance();) {
			AjoutOrdinateurDao.computerInformations(newCon, computer);
			logger.debug("Ajout d'un élément dans la BDD sans problème");
		} catch (SQLException e) {
			logger.error("Un problème est survenu lors de l'ajout d'un élément ou de l'accès a la base de données");
			throw new Exception("Impossible de se connecter a la base de donnees Ajout");
		} 
	}
}
