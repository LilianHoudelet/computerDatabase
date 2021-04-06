package com.excilys.formation.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UpdateDatabaseDao {
	
	public static final String REQUETE_UPDATE = "UPDATE computer SET introduced = ?, discontinued = ?, company_id = ?, name = ? WHERE id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateDatabaseDao.class);
	
	private DataSource dataSource;
	
	public UpdateDatabaseDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void updateComputerInformations(Computer computer) throws ClassNotFoundException, SQLException {
		JdbcTemplate update = new JdbcTemplate();
		update.setDataSource(dataSource);
		
		update.update(REQUETE_UPDATE, new Object[] { 
				computer.getDateSortie() != null ? computer.getDateSortie() : null, 
				computer.getDateRetrait() != null ? computer.getDateRetrait() : null, 
				computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null,
				computer.getName(),
				computer.getId() });

		
		logger.debug("Mise à jour de l'élément " + computer.getName() + " dans la base de données");
	}
}
