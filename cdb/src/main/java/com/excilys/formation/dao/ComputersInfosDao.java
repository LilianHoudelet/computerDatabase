package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComputersInfosDao {
	public static final String REQUETE_TOUT = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id";
	
	public static final String REQUETE_NOMBRE = "SELECT COUNT(id) from computer";
	
	public static final String REQUETE_PAGE = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id LIMIT ? OFFSET ?";

	public static final String REQUETE_DETAILS = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = ?";
	
	public static final String REQUETE_DETAILS_ID = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	
	public static ResultSet computerInformations(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE_TOUT);
					
		return rs;
	}
	
	public static ResultSet computerInformationsNbElts(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE_NOMBRE);
			
		return rs;
	}
	
	public static ResultSet computerInformationsDetails(Connection con, String nomMachine) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_DETAILS);
		stmt.setString(1, nomMachine);
		ResultSet rs = stmt.executeQuery();
			
		return rs;
	}
	
	public static ResultSet computerInformationsPage(Connection con, int taillePage, int page) throws SQLException {
				
		PreparedStatement stmt = con.prepareStatement(REQUETE_PAGE);
		
		stmt.setInt(1, taillePage);
		stmt.setInt(2, page * taillePage);
		
		ResultSet rs = stmt.executeQuery();
		return rs;
	}

	public static ResultSet computerInformationsDetails(Connection con, int id) throws SQLException {
		PreparedStatement stmt = con.prepareStatement(REQUETE_DETAILS_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		return rs;
	}
	
}
