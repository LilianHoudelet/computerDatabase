package com.excilys.formation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.formation.mapper.ComputerInfos;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputersInfosDao {
	public static final String REQUETE_TOUT = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id";
	
	public static final String REQUETE_NOMBRE_FILTRE = "SELECT count(computer.id) FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ?";
	
	public static final String REQUETE_PAGE_FILTRE_TRIE = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ";
	
	public static final String LIMIT_OFFSET = " LIMIT ? OFFSET ? ";
	
	public static final String REQUETE_DETAILS = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = ?";
	
	public static final String REQUETE_DETAILS_ID = "SELECT computer.id, computer.name, introduced, discontinued, company.name, computer.company_id FROM computer "
			+ "LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ?";
	
	JdbcTemplate template = new JdbcTemplate();
	
	public ComputersInfosDao(DataSource dataSource) {
		template.setDataSource(dataSource);
	}
	
	public List<Computer> computerInformations() {

		return template.query(REQUETE_TOUT, new ComputerInfos() );

	}

	public Integer computerInformationsNbEltsFiltre(String chaine) {
		
		return template.queryForObject(REQUETE_NOMBRE_FILTRE, Integer.class, chaine,chaine);
	}
	
	public List<Computer> computerInformationsPageFilterSorted(int taillePage, int page, String chaine, String order, String upOrDown) {
		
		return template.query(REQUETE_PAGE_FILTRE_TRIE + order + upOrDown + LIMIT_OFFSET, new ComputerInfos(), chaine,chaine, taillePage, page * taillePage);
	}

	
	public Computer computerInformationsDetails(String nomMachine) {
		return template.query(REQUETE_DETAILS, new ComputerInfos(), nomMachine).get(0);
	}

	public Computer computerInformationsDetails(int id) {
		return template.query(REQUETE_DETAILS_ID, new ComputerInfos(),id).get(0);
	}

}
