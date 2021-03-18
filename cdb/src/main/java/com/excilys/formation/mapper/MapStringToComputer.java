package com.excilys.formation.mapper;

import java.time.LocalDate;

import org.slf4j.Logger;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapStringToComputer {
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(MapStringToComputer.class);
		
	
	public static Computer ComputerStringToComputer(String name, String introduced, String discontinued, String companyId) throws Exception {
		
		LocalDate dateSortie = !introduced.isBlank() ? LocalDate.parse(introduced) : null ;
		LocalDate dateRetrait = !discontinued.isBlank() ? LocalDate.parse(discontinued) : null ;
		
		logger.debug("Passage des entrées en Objet String vers un Computer");
		
		return new Computer(0, name, dateSortie, dateRetrait, new Company(Integer.valueOf(companyId), ""));
	}
}
