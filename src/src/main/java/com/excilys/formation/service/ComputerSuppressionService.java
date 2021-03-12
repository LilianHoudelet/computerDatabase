package src.main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.dao.SupprimerDatabaseDao;
import src.main.java.com.excilys.formation.model.Computer;

public class ComputerSuppressionService {
	
	public static void supprDataOrdi(Computer computer) throws Exception {
		
		try (Connection con = AccessDatabase.getInstance();) {
			SupprimerDatabaseDao.computerInformations(con, computer);
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
}
