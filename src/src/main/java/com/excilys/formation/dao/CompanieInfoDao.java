package src.main.java.com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanieInfoDao {
	
	public static final String REQUETE = "SELECT id, name FROM company";
	
	public static ResultSet companyInformations(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE);
			
		return rs;
	}
}
