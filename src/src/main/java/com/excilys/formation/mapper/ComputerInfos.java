package src.main.java.com.excilys.formation.mapper;

//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.excilys.formation.model.Company;
import src.main.java.com.excilys.formation.model.Computer;

public class ComputerInfos {
	
	public static List<Computer> computerInformationsMapper(ResultSet computerInformations) throws SQLException {
		List<Computer> list = new ArrayList<Computer>();
		
		while (computerInformations.next()) {
			list.add(new Computer(computerInformations.getInt(1), computerInformations.getString(2)));
		}	
			
		return list; 
	}
	
	public static int computerInformationsMapperCount(ResultSet computerCount) throws SQLException {
		int compte = 0;
		if (computerCount.next()) {
			compte = computerCount.getInt(1);
		}
		return compte;
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
				return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie, new Company(computerInformations.getInt(6), computerInformations.getString(5))));
			} else {
				return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie, computerInformations.getDate(4).toLocalDate(), new Company(computerInformations.getInt(6), computerInformations.getString(5))));
			}
		} else {
			throw new Exception("Le mapping a raté");
		}
	}
}
