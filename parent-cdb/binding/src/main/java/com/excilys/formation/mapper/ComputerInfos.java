package com.excilys.formation.mapper;

//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ComputerInfos implements RowMapper<Computer> {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerInfos.class);

	public List<Computer> computerInformationsMapper(ResultSet computerInformations) throws SQLException {
		List<Computer> list = new ArrayList<Computer>();

		while (computerInformations.next()) {
			LocalDate dateSortie = computerInformations.getDate(3) != null
					? computerInformations.getDate(3).toLocalDate()
					: null;
			LocalDate dateRetrait = computerInformations.getDate(4) != null
					? computerInformations.getDate(4).toLocalDate()
					: null;

			list.add(new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie,
					dateRetrait, new Company(computerInformations.getInt(6), computerInformations.getString(5))));
		}
		logger.debug("Mappage du résultat de la requête vers une liste de Computer");
		return list;
	}

	public int computerInformationsMapperCount(ResultSet computerCount) throws SQLException {
		int compte = 0;
		if (computerCount.next()) {
			compte = computerCount.getInt(1);
		}
		logger.debug(
				"Mappage du résultat de la requête vers un entier correspondant au nombre d'éléments dans la base de données");
		return compte;
	}

	public Computer computerDetailsMapper(ResultSet computerInformations) throws SQLException, Exception {
		if (computerInformations.next()) {
			LocalDate dateSortie = computerInformations.getDate(3) != null
					? computerInformations.getDate(3).toLocalDate()
					: null;
			LocalDate dateRetrait = computerInformations.getDate(4) != null
					? computerInformations.getDate(4).toLocalDate()
					: null;
			logger.debug("Mappage du résultat de la requête vers un Computer");
			return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie,
					dateRetrait, new Company(computerInformations.getInt(6), computerInformations.getString(5))));

		} else {
			logger.error("Pas d'informations trouvées dans la base de données");
			throw new Exception("Pas d'informations trouvées dans la base de données");
		}
	}

	@Override
	public Computer mapRow(ResultSet computerInformations, int rowNum) throws SQLException {
		if (rowNum >= 0) {
			LocalDate dateSortie = computerInformations.getDate(3) != null
					? computerInformations.getDate(3).toLocalDate()
					: null;
			LocalDate dateRetrait = computerInformations.getDate(4) != null
					? computerInformations.getDate(4).toLocalDate()
					: null;
			logger.debug("Mappage du résultat de la requête vers un Computer");
			return (new Computer(computerInformations.getInt(1), computerInformations.getString(2), dateSortie,
					dateRetrait, new Company(computerInformations.getInt(6), computerInformations.getString(5))));

		} else {
			return (new Computer(0,"error",null, null, null));
		}
	}
}
