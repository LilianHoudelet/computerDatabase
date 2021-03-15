package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanieInfoDao {
	
	public static final String REQUETE = "SELECT id, name FROM company";
	
	public static final String REQUETE_ID = "SELECT id, name FROM company WHERE name = ?";
	
	public static ResultSet companyInformations(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE);
			
		return rs;
	}
	
	public static ResultSet companyInformationsId(Connection con, String name) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_ID);
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Fine");	
		return rs;
	}
	
}
