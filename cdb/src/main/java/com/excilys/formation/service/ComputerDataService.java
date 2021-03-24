package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

public class ComputerDataService {
	
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDataService.class);
	
	public static List<Computer> recupDataOrdi() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération d'une liste de computer");
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformations(con));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPage(int nombreParPage, int page) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération d'une liste de computer plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPage(con, nombreParPage, page));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPageTrie(int nombreParPage, int page) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération d'une liste de computer triée plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPageOrdreNom(con, nombreParPage, page));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static int recupDataOrdiNombre() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			logger.debug("Récupération du nombre de computer plus petite");
			return ComputerInfos.computerInformationsMapperCount(ComputersInfosDao.computerInformationsNbElts(con));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche nombre de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Compte");
		} 
	}
}
