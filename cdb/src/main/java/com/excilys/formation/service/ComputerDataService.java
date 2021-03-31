package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.mapper.RequestFilterString;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerDataService {
	
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDataService.class);
	
	static AccessDatabase instance = AccessDatabase.getInstance();
	
	public static List<Computer> recupDataOrdi() throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération d'une liste de computer");
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformations(con));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPage(int nombreParPage, int page) throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération d'une liste de computer plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPage(con, nombreParPage, page));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPageTrie(int nombreParPage, int page, String order) throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération d'une liste de computer Filtrée plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPageOrdreNom(con, nombreParPage, page, RequestFilterString.convertOrderString(order)));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page filtrée de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPageFiltre(int nombreParPage, int page, String chaine) throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération d'une liste de computer triée plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPageFilter(con, nombreParPage, page, chaine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static List<Computer> recupDataOrdiPageFiltreTrie(int nombreParPage, int page, String chaine, String order, boolean ascendance) throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération d'une liste de computer filtrée triée plus petite : Page " + page);
			return ComputerInfos.computerInformationsMapper(ComputersInfosDao.computerInformationsPageFilterSorted(con, nombreParPage, page, chaine, RequestFilterString.convertOrderString(order), RequestFilterString.convertOrderbool(ascendance)));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page triée filtrée de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public static int recupDataOrdiNombre() throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération du nombre de computer");
			return ComputerInfos.computerInformationsMapperCount(ComputersInfosDao.computerInformationsNbElts(con));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche nombre de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Compte");
		} 
	}
	
	public static int recupDataOrdiNombre(String chaine) throws Exception {
		try (Connection con = instance.getConnection();) {
			logger.debug("Récupération du nombre de computer plus petite");
			return ComputerInfos.computerInformationsMapperCount(ComputersInfosDao.computerInformationsNbEltsFiltre(con,chaine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche nombre de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Compte");
		} 
	}
	
}
