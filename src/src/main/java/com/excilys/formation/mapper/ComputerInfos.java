package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.model.Company;
import src.main.java.com.excilys.formation.model.Computer;

public class ComputerInfos {
	
	public static final String REQUETE = "select id, name from computer";
	
	public static final String REQUETE_PAGE = "select id, name from computer LIMIT ";
	
	public static final String REQUETE_OFFSET = " OFFSET ";
	
	/*public static List<Computer> computerInformationsMapper(ResultSet ordis) throws ClassNotFoundException, SQLException {
		List<Computer> list = new ArrayList<Computer>();
		
		while (ordis.next()) {
			list.add(new Computer(ordis.getInt(1), ordis.getString(2)));
		}	
			
		return list; 	
	}
	*/
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

	public static List<Computer> computerInformationsMapper(ResultSet computerInformations) throws SQLException {
		List<Computer> list = new ArrayList<Computer>();
		
		while (computerInformations.next()) {
			list.add(new Computer(computerInformations.getInt(1), computerInformations.getString(2)));
		}	
			
		return list; 
	}

	public static Computer computerDetailsMapper(ResultSet computerInformations) throws SQLException, Exception {
		if (computerInformations.next()) {
			LocalDate dateSortie;
			if (computerInformations.getDate(3) == null) {
				dateSortie = null;
			} else {
				dateSortie = computerInformations.getDate(3).toLocalDate();
			}
			if (computerInformations.getDate(4) == null) {
				return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie, new Company(computerInformations.getInt(5), computerInformations.getString(6))));
			} else {
				return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie, computerInformations.getDate(4).toLocalDate(), new Company(computerInformations.getInt(5), computerInformations.getString(6))));
			}
		} else {
			throw new Exception("");
		}
	}
}
