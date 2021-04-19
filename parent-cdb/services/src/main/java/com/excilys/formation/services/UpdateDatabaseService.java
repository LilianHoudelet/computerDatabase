package com.excilys.formation.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.UpdateDatabaseDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UpdateDatabaseService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseService.class);
	
	private UpdateDatabaseDao updateDatabaseDao;

	public UpdateDatabaseService(UpdateDatabaseDao updateDatabaseDao) {
		this.updateDatabaseDao = updateDatabaseDao;
	}
	
	public void updateDataService(Computer computer) {
		updateDatabaseDao.updateComputerInformations(computer);
		logger.debug("Mise a jour de " + computer.getName() + " dans la BDD");
			
	} 
}

