package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.AjoutOrdinateurDao;
import com.excilys.formation.model.Computer;

public class AjoutOrdinateurService {
	public static void ajoutDataService(Computer computer) throws Exception {
		try (Connection newCon = AccessDatabase.getInstance();) {
			AjoutOrdinateurDao.computerInformations(newCon, computer);
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Ajout");
		} 
	}
}
