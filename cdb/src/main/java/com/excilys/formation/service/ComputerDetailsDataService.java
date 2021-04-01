package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerDetailsDataService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDetailsDataService.class);
	
	@Autowired
	private DataSource dataSource;
	//static AccessDatabase instance = AccessDatabase.getInstance();
	
	public Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		
		try (Connection newCon = dataSource.getConnection();) {
			logger.debug("récupération des détails de la machine "+ nomMachine);
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, nomMachine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche détails de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}
	
	public Computer recupDataDetailsOrdi(int id) throws Exception {
		
		try (Connection newCon = dataSource.getConnection();) {
			logger.debug("récupération des détails de la machine "+ id);
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, id));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche détails de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}


}
