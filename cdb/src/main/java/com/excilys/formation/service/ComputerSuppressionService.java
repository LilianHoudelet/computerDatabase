package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.SupprimerDatabaseDao;
import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerSuppressionService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerSuppressionService.class);
	
	@Autowired
	private DataSource dataSource;
	//static AccessDatabase instance = AccessDatabase.getInstance();
	
	public void supprDataOrdi(Computer computer) throws Exception {
		
		try (Connection con = dataSource.getConnection();) {
			logger.debug("Appel suppression élément " + computer.getName() + " de la BDD");
			SupprimerDatabaseDao.computerInformations(con, computer);
			
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression");
		} 
	}
	
	public void supprDataOrdiId(int id) throws Exception {
		
		try (Connection con = dataSource.getConnection();) {
			logger.debug("Appel suppression élément " + id + " de la BDD");
			SupprimerDatabaseDao.deleteComputer(con, id);
			
		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression");
		} 
	}
}
