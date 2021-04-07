package com.excilys.formation.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dao.DeleteCompanyDao;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyService.class);

	private DeleteCompanyDao deleteCompanyDao;
	
	public DeleteCompanyService(DeleteCompanyDao deleteCompanyDao) {
		this.deleteCompanyDao = deleteCompanyDao;
	}

	@Transactional
	public void supprDataCompanyId(int id) throws DataAccessException {

		try {
			logger.debug("Appel suppression company " + id + " de la BDD");
			
			deleteCompanyDao.deleteCompany(id);

		} catch (DataAccessException e) {
			logger.error("Erreur dans la suppression de la companie dans la table",e);
			throw e;
		}
	}

}
