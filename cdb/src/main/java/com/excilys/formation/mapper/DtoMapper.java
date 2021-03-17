package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class DtoMapper {
	
	public static List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {
		String nullString = "None";
		
		List<ComputerDTO> listeComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			
			String dateSortie;
			String dateRetrait;
			
			if (computer.getDateSortie() != null) {
				dateSortie = computer.getDateSortie().toString();
			} else {
				dateSortie = nullString;
			}
			if (computer.getDateRetrait() != null) {
				dateRetrait = computer.getDateRetrait().toString();
			} else {
				dateRetrait = nullString;
			}
			
			listeComputerDto.add(new ComputerDTO(String.valueOf(computer.getId()), computer.getName(), dateSortie, dateRetrait, computer.getCompany().getName()));
		}
		return listeComputerDto;
	}
	
public static List<CompanyDTO> mapCompanyToCompanyDTO(List<Company> companies) {
		
		List<CompanyDTO> listeCompaniesDto = new ArrayList<CompanyDTO>();
		for (Company company : companies) {
			listeCompaniesDto.add(new CompanyDTO(company.getId(), company.getName()));
			}
		return listeCompaniesDto;
	}
}
