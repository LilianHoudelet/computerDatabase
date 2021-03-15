package main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.com.excilys.formation.dao.AccessDatabase;
import main.java.com.excilys.formation.dao.ComputersInfosDao;
import main.java.com.excilys.formation.mapper.ComputerInfos;
import main.java.com.excilys.formation.model.Computer;

public class ComputerDetailsDataService {
	public static Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		
		try (Connection newCon = AccessDatabase.getInstance();) {
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, nomMachine));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}
}
