package com.excilys.formation.mapper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.AddComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MapStringToComputer {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(MapStringToComputer.class);

	public Computer ComputerStringToComputer(String name, String introduced, String discontinued, String companyId)
			throws Exception {

		LocalDate dateSortie = !introduced.isBlank() ? LocalDate.parse(introduced) : null;
		LocalDate dateRetrait = !discontinued.isBlank() ? LocalDate.parse(discontinued) : null;

		logger.debug("Passage des entrées en Objet String vers un Computer");

		return new Computer(0, name, dateSortie, dateRetrait, new Company(Integer.valueOf(companyId), ""));
	}

	public Computer ComputerStringToComputer(AddComputerDTO computer) {

		LocalDate dateSortie = !computer.getDateSortie().isBlank() ? LocalDate.parse(computer.getDateSortie()) : null;
		LocalDate dateRetrait = !computer.getDateRetrait().isBlank() ? LocalDate.parse(computer.getDateRetrait()) : null;
		int id = computer.getId() != null ? Integer.parseInt(computer.getId()) : 0;
 
		logger.debug("Passage des entrées en AddComputerDTO vers un Computer");

		return new Computer(id, computer.getNom(), dateSortie, dateRetrait, new Company(Integer.valueOf(computer.getCompanyId()), ""));
	}

}
