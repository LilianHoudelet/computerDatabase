package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public ResultSet companyInformations(Connection con) throws ClassNotFoundException, SQLException {
		
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE);
		
		logger.debug("Récupération de la liste des constructeurs");
		
		return rs;
	}
	
	public List<Company> companyInformations() throws ClassNotFoundException, SQLException {
		
		logger.debug("Récupération de la liste des constructeurs");
		
		return template.query(REQUETE, new CompanyInfos() );
	}
	
	public List<Company> companyInformationsId(String name) throws ClassNotFoundException, SQLException {

		logger.debug("Récupération de l'id correspondant au nom du constructeur : " + name);
		
		return template.query(REQUETE_ID, new Object[] { name }, new CompanyInfos() );
	}
	
	public ResultSet companyInformationsId(Connection con, String name) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_ID);
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();	
		
		logger.debug("Récupération de l'id correspondant au nom du constructeur : " + name);
		
		return rs;
	}
	
}
