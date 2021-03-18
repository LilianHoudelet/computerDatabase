package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

public class ComputerDetailsDataService {
	public static Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		
		Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDetailsDataService.class);
		
		try (Connection newCon = AccessDatabase.getInstance();) {
			logger.debug("récupération des détails de la machine "+ nomMachine);
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, nomMachine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche détails de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}
}
