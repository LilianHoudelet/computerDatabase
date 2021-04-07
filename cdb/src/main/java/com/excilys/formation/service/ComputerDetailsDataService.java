package com.excilys.formation.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerDetailsDataService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDetailsDataService.class);

	private ComputersInfosDao computersInfosDao;

	public ComputerDetailsDataService(ComputersInfosDao computersInfosDao) {
		this.computersInfosDao = computersInfosDao;
	}

	public Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		logger.debug("récupération des détails de la machine " + nomMachine);
		return computersInfosDao.computerInformationsDetails(nomMachine);
	}

	public Computer recupDataDetailsOrdi(int id) throws Exception {
		logger.debug("récupération des détails de la machine " + id);
		return computersInfosDao.computerInformationsDetails(id);
	}

}
