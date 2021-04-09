package com.excilys.formation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.formation.mapper.CompanyInfos;
import com.excilys.formation.model.Company;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CompanieInfoDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanieInfoDao.class);
	
	public static final String REQUETE = "SELECT id, name FROM company";
	
	public static final String REQUETE_ID = "SELECT id, name FROM company WHERE name = ?";
	
	JdbcTemplate template = new JdbcTemplate();
	
	public CompanieInfoDao(DataSource dataSource) {
		template.setDataSource(dataSource);
	}
	
	public List<Company> companyInformations() {
		
		logger.debug("Récupération de la liste des constructeurs");
		
		return template.query(REQUETE, new CompanyInfos() );
	}
	
	public Company companyInformationsId(String name) {

		logger.debug("Récupération de l'id correspondant au nom du constructeur : " + name);
		
		return template.query(REQUETE_ID,new CompanyInfos(),name).get(0);
	}
		
}
