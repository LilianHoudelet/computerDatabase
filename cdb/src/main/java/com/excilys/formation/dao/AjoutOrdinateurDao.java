package com.excilys.formation.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AjoutOrdinateurDao {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurDao.class);

	public static final String REQUETE_AJOUTER_COMPLET = "INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?)";
	
	JdbcTemplate insert = new JdbcTemplate();
	
	public AjoutOrdinateurDao(DataSource dataSource) {
		insert.setDataSource(dataSource);
	}

	public void computerInformations(Computer computer) {
		insert.update(REQUETE_AJOUTER_COMPLET, new Object[] { 
				computer.getId(), 
				computer.getName(), 
				computer.getDateSortie() != null ? computer.getDateSortie() : null, 
				computer.getDateRetrait() != null ? computer.getDateRetrait() : null, 
				computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null });
		
	}
}
