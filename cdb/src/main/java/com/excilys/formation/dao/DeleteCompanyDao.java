package com.excilys.formation.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyDao {
	
	public final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ? ";
	
	public final static String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyDao.class);
	
	JdbcTemplate delete = new JdbcTemplate();
	
	public DeleteCompanyDao(DataSource dataSource) {
		delete.setDataSource(dataSource);
	}
		
	@Transactional
	public void deleteCompany(int companyId) {
		delete.update(DELETE_COMPUTER, companyId );
		delete.update(DELETE_COMPANY, companyId );
		logger.debug("Suppression de l'element id et des computer associes a l'id " + companyId);
	}
}
