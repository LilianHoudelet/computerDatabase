package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.excilys.formation.model.Computer;

public class ComputerInfos {
	
	public static final String REQUETE = "select id, name from computer";
	
	public static final String REQUETE_PAGE = "select id, name from computer LIMIT ";
	
	public static final String REQUETE_OFFSET = " OFFSET ";
	
	public static List<Computer> computerInformations() throws ClassNotFoundException, SQLException {
		List<Computer> list = new ArrayList<Computer>();
		
		try (Connection con = AccessDatabase.getInstance();) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(REQUETE);  
			
			while (rs.next()) {
				list.add(new Computer(rs.getInt(1), rs.getString(2)));
			}	
			con.close();
			return list; 	
		}
	}
	
	public static List<Computer> computerInformationsPage(int nbValeurParPage, int page) throws ClassNotFoundException, SQLException {
		List<Computer> list = new ArrayList<Computer>();
		
		try (Connection con = AccessDatabase.getInstance();) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(REQUETE + nbValeurParPage + REQUETE_OFFSET + nbValeurParPage * page);
			
			while (rs.next()) {
				list.add(new Computer(rs.getInt(1), rs.getString(2)));
			}	
			con.close();
			return list; 	
		}
	}	
}
