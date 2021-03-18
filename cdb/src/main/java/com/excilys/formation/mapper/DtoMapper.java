package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class DtoMapper {
	
	public static List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {
		String nullString = "Unknown";
		
		List<ComputerDTO> listeComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			
			String dateSortie;
			String dateRetrait;
			
			dateSortie = computer.getDateSortie() == null ? nullString : computer.getDateSortie().toString();
			dateRetrait = computer.getDateRetrait() == null ? nullString : computer.getDateRetrait().toString();
			
			
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
