package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

//import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.AjoutOrdinateurDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AjoutOrdinateurService {
	
	private DataSource dataSource;	
	private AjoutOrdinateurDao ajoutOrdinateurDao;

	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurService.class);
	
	public AjoutOrdinateurService(DataSource dataSource, AjoutOrdinateurDao ajoutOrdinateurDao) {
		this.dataSource = dataSource;
		this.ajoutOrdinateurDao = ajoutOrdinateurDao;
	}
		
	public void ajoutDataService(Computer computer) throws Exception {
		try (Connection newCon = dataSource.getConnection();) {
			ajoutOrdinateurDao.computerInformations(newCon, computer);
			logger.debug("Ajout d'un élément dans la BDD sans problème");
		} catch (SQLException e) {
			logger.error("Un problème est survenu lors de l'ajout d'un élément ou de l'accès a la base de données");
			throw new Exception("Impossible de se connecter a la base de donnees Ajout");
		} 
	}
}
