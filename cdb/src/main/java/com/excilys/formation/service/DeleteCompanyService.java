package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.dao.DeleteCompanyDao;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyService.class);

	private DataSource dataSource;
	private DeleteCompanyDao deleteCompanyDao;
	
	public DeleteCompanyService(DataSource dataSource, DeleteCompanyDao deleteCompanyDao) {
		this.dataSource = dataSource;
		this.deleteCompanyDao = deleteCompanyDao;
	}

	public void supprDataCompanyId(int id) throws Exception {

		try (Connection con = dataSource.getConnection();) {
			logger.debug("Appel suppression company " + id + " de la BDD");
			
			deleteCompanyDao.deleteCompany(con, id);

		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression company");
		}
	}

}
