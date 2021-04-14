package com.excilys.formation.service;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dao.ComputersInfosDao;
import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.mapper.RequestFilterString;
import com.excilys.formation.model.Computer;
import com.excilys.formation.model.ComputerPage;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerDataService {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerDataService.class);

	private RequestFilterString sortingString;
	private ComputersInfosDao computersInfosDao;

	public ComputerDataService(DataSource dataSource, RequestFilterString sortingString, ComputerInfos computerMapper,
			ComputersInfosDao computersInfosDao) {
		this.sortingString = sortingString;
		this.computersInfosDao = computersInfosDao;
	}

	public List<Computer> recupDataOrdi() throws Exception {
		logger.debug("Récupération d'une liste de computer");
		return computersInfosDao.computerInformations();
	}

	public List<Computer> recupDataOrdiPage(int nombreParPage, int page) {
		return recupDataOrdiPageFiltreTrie(nombreParPage, page, "", "id", true);
	}

	public List<Computer> recupDataOrdiPageFiltreTrie(int nombreParPage, int page, String chaine, String order,
			boolean ascendance) {
		logger.debug("Récupération d'une liste de computer filtrée triée plus petite : Page " + page);
		return computersInfosDao.computerInformationsPageFilterSorted(nombreParPage, page, "%"+chaine+"%",
				sortingString.convertOrderString(order), sortingString.convertOrderbool(ascendance));
	}

	public int recupDataOrdiNombre(String chaine) {
		logger.debug("Récupération du nombre de computer plus petite");
		return computersInfosDao.computerInformationsNbEltsFiltre("%"+chaine+"%");
	}

	public List<Computer> recupDataOrdiPageFiltreTrie(ComputerPage page) {
		logger.debug("Récupération d'une liste de computer filtrée triée plus petite par page: Page " + page.getNumPage());
		return computersInfosDao.computerInformationsPageFilterSorted(page.getNbEltsParPage(), page.getNumPage() -1, "%"+page.getSearchString()+"%",
				sortingString.convertOrderString(page.getOrderBy()), sortingString.convertOrderbool(page.getAsc()));
	}
}
