package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class DtoMapper {
	
	public static List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {
		
		List<ComputerDTO> listeComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			listeComputerDto.add(new ComputerDTO(String.valueOf(computer.getId()), computer.getName(), computer.getDateSortie().toString(), computer.getDateRetrait().toString(), computer.getCompany().getName()));
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
