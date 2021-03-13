package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.model.Company;

//import java.util.ArrayList;
//import java.util.List;

import src.main.java.com.excilys.formation.model.Computer;

/**
 * Sortie : Liste d'ordinateurs pour pouvoir sortir le fait  
 * 
 * 
 * @author excilys
 *
 */
public class ChercherDetails {
	
	public static final String REQUETE = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = '";
	
	public static Computer details(String s) throws ClassNotFoundException, SQLException {
		
		String requete = new String(REQUETE + s + "'");
		
		Connection con = AccessDatabase.getInstance();  
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(requete);  
		

		if (rs.next()) {  // si il y en a un 
		
			LocalDate dateSortie;
			if (rs.getDate(3) == null) {
				dateSortie = null;
			} else {
				dateSortie = rs.getDate(3).toLocalDate();
			}
			if (rs.getDate(4) == null) {
				return (new Computer(rs.getInt(1), rs.getString(2), dateSortie, new Company(rs.getInt(6), rs.getString(5))));
			} else {
				return (new Computer(rs.getInt(1), rs.getString(2), dateSortie, rs.getDate(4).toLocalDate(), new Company(rs.getInt(6), rs.getString(5))));
			}
			
		}
		//con.close();
		// sinon, on entre pas et on ressort null
		return null;
	}
}
