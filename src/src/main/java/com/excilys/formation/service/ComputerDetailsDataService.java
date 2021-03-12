package src.main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.dao.ComputersInfosDao;
import src.main.java.com.excilys.formation.mapper.ComputerInfos;
import src.main.java.com.excilys.formation.model.Computer;

public class ComputerDetailsDataService {
	public static Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(con, nomMachine));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
}
