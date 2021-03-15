package src.main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.dao.ComputersInfosDao;
import src.main.java.com.excilys.formation.mapper.ComputerInfos;
import src.main.java.com.excilys.formation.model.Computer;

public class ComputerDataService {
	
	public static List<Computer> recupDataOrdi() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformations(con));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPage(int nombreParPage, int page) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPage(con, nombreParPage, page));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static int recupDataOrdiNombre() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			return ComputerInfos.computerInformationsMapperCount(ComputersInfosDao.computerInformationsNbElts(con));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Compte");
		} 
	}
}
