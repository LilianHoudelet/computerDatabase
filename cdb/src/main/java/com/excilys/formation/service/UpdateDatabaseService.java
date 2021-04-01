package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.UpdateDatabaseDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UpdateDatabaseService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseService.class);
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UpdateDatabaseDao updateDatabaseDao;
	
	public void updateDataService(Computer computer) throws Exception {
		try (Connection newCon = dataSource.getConnection();) {
			logger.debug("Mise a jour de " + computer.getName() + " dans la BDD");
			updateDatabaseDao.updateComputerInformations(newCon, computer);
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression dans la BDD");
			throw new Exception("Impossible de se connecter a la base de donnees Update");
		} 
	}
}
