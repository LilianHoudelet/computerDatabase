package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputersInfosDao {
	public static final String REQUETE_TOUT = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id";
	
	public static final String REQUETE_NOMBRE_FILTRE = "SELECT count(computer.id) FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ?";
	
	public static final String REQUETE_PAGE_FILTRE_TRIE = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ";
	
	public static final String LIMIT_OFFSET = " LIMIT ? OFFSET ? ";
	
	public static final String REQUETE_DETAILS = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = ?";
	
	public static final String REQUETE_DETAILS_ID = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	
	public ResultSet computerInformations(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE_TOUT);
					
		return rs;
	}

	public ResultSet computerInformationsNbEltsFiltre(Connection con, String chaine) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_NOMBRE_FILTRE);
		stmt.setString(1, "%"+chaine+"%");
		stmt.setString(2, "%"+chaine+"%");
		ResultSet rs = stmt.executeQuery();
			
		return rs;
	}
		
	public ResultSet computerInformationsPageFilterSorted(Connection con, int taillePage, int page, String chaine, String order, String upOrDown) throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement(REQUETE_PAGE_FILTRE_TRIE + order + upOrDown + LIMIT_OFFSET);
				
		stmt.setString(1, "%"+chaine+"%");
		stmt.setString(2, "%"+chaine+"%");
		stmt.setInt(3, taillePage);
		stmt.setInt(4, page * taillePage);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}


	public ResultSet computerInformationsDetails(Connection con, String nomMachine) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_DETAILS);
		stmt.setString(1, nomMachine);
		ResultSet rs = stmt.executeQuery();
			
		return rs;
	}

	public ResultSet computerInformationsDetails(Connection con, int id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(REQUETE_DETAILS_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		return rs;
	}
	
}
