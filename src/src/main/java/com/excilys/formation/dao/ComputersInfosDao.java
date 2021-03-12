package src.main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComputersInfosDao {
	public static final String REQUETE = "select id, name from computer";
	
	public static final String REQUETE_PAGE = "select id, name from computer LIMIT ";
	
	public static final String REQUETE_OFFSET = " OFFSET ";
	
	public static final String REQUETE_DETAILS = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = '";
	

	public static ResultSet computerInformations(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE);
			
		return rs;
	}
	
	public static ResultSet computerInformationsDetails(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE_DETAILS);
			
		return rs;
	}
	
}
