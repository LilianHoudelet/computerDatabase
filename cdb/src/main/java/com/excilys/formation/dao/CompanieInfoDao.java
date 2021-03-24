package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

public class CompanieInfoDao {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(CompanieInfoDao.class);
	
	public static final String REQUETE = "SELECT id, name FROM company";
	
	public static final String REQUETE_ID = "SELECT id, name FROM company WHERE name = ?";
	
	public static ResultSet companyInformations(Connection con) throws ClassNotFoundException, SQLException {
		
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE);
		
		logger.debug("Récupération de la liste des constructeurs");
		
		return rs;
	}
	
	public static ResultSet companyInformationsId(Connection con, String name) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_ID);
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();	
		
		logger.debug("Récupération de l'id correspondant au nom du constructeur : " + name);
		
		return rs;
	}
	
}
