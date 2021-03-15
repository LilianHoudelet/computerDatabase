package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

public class ComputerDetailsDataService {
	public static Computer recupDataDetailsOrdi(String nomMachine) throws Exception {
		
		try (Connection newCon = AccessDatabase.getInstance();) {
			return ComputerInfos.computerDetailsMapper(ComputersInfosDao.computerInformationsDetails(newCon, nomMachine));
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Details");
		} 
	}
}
