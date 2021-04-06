package com.excilys.formation.service;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.SupprimerDatabaseDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerSuppressionService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerSuppressionService.class);

	private SupprimerDatabaseDao supprimerDatabaseDao;
	
	public ComputerSuppressionService(DataSource dataSource, SupprimerDatabaseDao supprimerDatabaseDao) {
		this.supprimerDatabaseDao = supprimerDatabaseDao;
	}
	
	public void supprDataOrdi(Computer computer) throws Exception {
		logger.debug("Appel suppression élément " + computer.getName() + " de la BDD");
		supprimerDatabaseDao.deleteComputer(computer.getName());
	}
	public void supprDataOrdiId(int id) throws Exception {
		logger.debug("Appel suppression élément " + id + " de la BDD");
		supprimerDatabaseDao.deleteComputer(id);
	}
	
}
