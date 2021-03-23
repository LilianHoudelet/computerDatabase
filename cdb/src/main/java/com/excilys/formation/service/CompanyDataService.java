package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.CompanieInfoDao;
import com.excilys.formation.mapper.CompanyInfos;
import com.excilys.formation.model.Company;

public class CompanyDataService {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanyDataService.class);
	
	public static List<Company> recupDataCompany() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération de la liste des Company");
			return CompanyInfos.companyInformationsMapper(CompanieInfoDao.companyInformations(con));
		
		} catch (SQLException e) {
			logger.error("Erreur sur l'accès a la base de données ou sur le mappage des informations");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	public static Company recupDataCompanyId(String nomConstructeur) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération de l'Id de Company avec le nom");
			return CompanyInfos.companyInformationsMapperId(CompanieInfoDao.companyInformationsId(con, nomConstructeur));
		
		} catch (Exception e) {
			logger.error("Erreur sur l'accès a la base de données ou sur la récupération de l'Id");
			throw new Exception("Impossible de se connecter a la base de donnees Id");
		} 
	}
}
