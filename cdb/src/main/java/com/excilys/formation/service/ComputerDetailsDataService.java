package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

public class ComputerDetailsDataService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDetailsDataService.class);
	
	static AccessDatabase instance = AccessDatabase.getInstance();
	
	public static Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		
		try (Connection newCon = instance.getConnection();) {
			logger.debug("récupération des détails de la machine "+ nomMachine);
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, nomMachine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche détails de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}
	
public static Computer recupDataDetailsOrdi(int id) throws Exception {
		
		try (Connection newCon = instance.getConnection();) {
			logger.debug("récupération des détails de la machine "+ id);
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, id));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche détails de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}


}
