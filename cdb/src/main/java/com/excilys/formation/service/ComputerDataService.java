package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.mapper.RequestFilterString;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerDataService {
	
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDataService.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private RequestFilterString sortingString;
	
	@Autowired
	private ComputerInfos computerMapper;
	
	@Autowired
	private ComputersInfosDao computersInfosDao;
	
	public List<Computer> recupDataOrdi() throws Exception {
		try (Connection con = dataSource.getConnection();) {
			logger.debug("Récupération d'une liste de computer");
			return computerMapper.computerInformationsMapper(computersInfosDao.computerInformations(con));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	
	public List<Computer> recupDataOrdiPage(int nombreParPage, int page) throws Exception {
		return recupDataOrdiPageFiltreTrie(nombreParPage, page, "", "id", true);
	}
			
	public List<Computer> recupDataOrdiPageFiltreTrie(int nombreParPage, int page, String chaine, String order, boolean ascendance) throws Exception {
		try (Connection con = dataSource.getConnection();) {
			logger.debug("Récupération d'une liste de computer filtrée triée plus petite : Page " + page);
			return computerMapper.computerInformationsMapper(computersInfosDao.computerInformationsPageFilterSorted(con, nombreParPage, page, chaine, sortingString.convertOrderString(order), sortingString.convertOrderbool(ascendance)));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche page triée filtrée de computer");
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}

	public int recupDataOrdiNombre(String chaine) throws Exception {
		try (Connection con = dataSource.getConnection();) {
			logger.debug("Récupération du nombre de computer plus petite");
			return computerMapper.computerInformationsMapperCount(computersInfosDao.computerInformationsNbEltsFiltre(con,chaine));
		} catch (SQLException e) {
			logger.error("Impossible de se connecter a la BDD, recherche nombre de computer");
			throw new Exception("Impossible de se connecter a la base de donnees Compte");
		} 
	}
	
}
