package com.excilys.formation.mapper;

import java.time.LocalDate;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapStringToComputer {
	public static Computer ComputerStringToComputer(String name, String introduced, String discontinued, String companyId) throws Exception {
		
		LocalDate dateSortie = !introduced.isBlank() ? LocalDate.parse(introduced) : null ;
		LocalDate dateRetrait = !discontinued.isBlank() ? LocalDate.parse(discontinued) : null ;
		
		return new Computer(0, name, dateSortie, dateRetrait, new Company(Integer.valueOf(companyId), ""));
	}
}
