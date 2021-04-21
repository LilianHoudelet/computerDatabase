package com.excilys.formation.services;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.CompanieInfoDao;
import com.excilys.formation.model.Company;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CompanyDataService {

	private CompanieInfoDao companieInfoDao;

	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanyDataService.class);

	public CompanyDataService(CompanieInfoDao companieInfoDao) {

		this.companieInfoDao = companieInfoDao;
	}

	public List<Company> recupDataCompany(){
		logger.debug("Récupération de la liste des Company");
		return companieInfoDao.companyInformations();
	}

	public Company recupDataCompanyId(String nomConstructeur) {
		logger.debug("Récupération de l'Id de Company avec le nom");
		return companieInfoDao.companyInformationsId(nomConstructeur);
	}
}
