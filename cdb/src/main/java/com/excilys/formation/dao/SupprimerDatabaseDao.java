package com.excilys.formation.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SupprimerDatabaseDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(SupprimerDatabaseDao.class);
	
	public static final String REQUETE_SUPPRIMER = "DELETE FROM computer WHERE name = ?";
	
	public static final String REQUETE_SUPPRIMER_PAR_ID = "DELETE FROM computer WHERE id = ?";
	
	JdbcTemplate delete = new JdbcTemplate();
	
	public SupprimerDatabaseDao(DataSource dataSource) {
		delete.setDataSource(dataSource);
	}
	
	public void deleteComputer(String name) {		
        delete.update(REQUETE_SUPPRIMER, new Object[] { name });
	}
	
	public void deleteComputer(int id) {
        delete.update(REQUETE_SUPPRIMER_PAR_ID, new Object[] { id });
	}
}
