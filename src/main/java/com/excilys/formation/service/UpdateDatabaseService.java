package main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.com.excilys.formation.dao.AccessDatabase;
import main.java.com.excilys.formation.dao.UpdateDatabaseDao;
import main.java.com.excilys.formation.model.Computer;

public class UpdateDatabaseService {
	public static void updateDataService(Computer computer) throws Exception {
		try (Connection newCon = AccessDatabase.getInstance();) {
			UpdateDatabaseDao.updateComputerInformations(newCon, computer);
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Ajout");
		} 
	}
}
