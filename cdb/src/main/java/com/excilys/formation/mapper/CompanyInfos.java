package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.excilys.formation.model.Company;

public class CompanyInfos {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanyInfos.class);
	
	public static List<Company> companyInformationsMapper(ResultSet companyInformations) throws SQLException {
		List<Company> list = new ArrayList<Company>();
		
		while (companyInformations.next()) {
			list.add(new Company(companyInformations.getInt(1), companyInformations.getString(2)));
		}			
		logger.debug("Mappage de la requête en Liste de Company");
		return list; 
	}
	
	public static Company companyInformationsMapperId(ResultSet companyInformations) throws Exception {
		if (companyInformations.next()) {
			logger.debug("Création d'un objet Company avec les informations de la requête");
			return new Company(companyInformations.getInt(1), companyInformations.getString(2));	
		}	else {
			logger.debug("Création d'une Company vide pour l'ajout dans la table");
			return new Company(0,"");
		}
	}
	
	
}