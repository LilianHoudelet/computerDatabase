package com.excilys.formation.services;

import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.excilys.formation.dao.AjoutOrdinateurDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AjoutOrdinateurService {
		
	private AjoutOrdinateurDao ajoutOrdinateurDao;

	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurService.class);
	
	public AjoutOrdinateurService(AjoutOrdinateurDao ajoutOrdinateurDao) {
		this.ajoutOrdinateurDao = ajoutOrdinateurDao;
	}
	
	public void ajoutDataService(Computer computer) {	
		ajoutOrdinateurDao.computerInformations(computer);
		logger.debug("Ajout d'un élément dans la BDD sans problème");
	} 
}
	

