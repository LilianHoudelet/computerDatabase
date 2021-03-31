package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.DeleteCompanyDao;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyService.class);

	static AccessDatabase instance = AccessDatabase.getInstance();

	public static void supprDataCompanyId(int id) throws Exception {

		try (Connection con = instance.getConnection();) {
			logger.debug("Appel suppression company " + id + " de la BDD");
			
			DeleteCompanyDao.deleteCompany(con, id);

		} catch (SQLException e) {
			logger.error("Erreur dans la suppression de la table");
			throw new Exception("Impossible de se connecter a la base de donnees Suppression company");
		}
	}

}
