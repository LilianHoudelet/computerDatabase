package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class DtoMapper {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(DtoMapper.class);

	public static List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {

		List<ComputerDTO> listeComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {

			listeComputerDto.add(mapComputerToComputerDTOOne(computer));
		}
		logger.debug("Passage d'une liste de Computer vers sont equivalent réduit ComputerDTO");
		return listeComputerDto;
	}

	public static ComputerDTO mapComputerToComputerDTOOne(Computer computer) {
		String nullString = "Unknown";

		String dateSortie;
		String dateRetrait;

		dateSortie = computer.getDateSortie() == null ? nullString : computer.getDateSortie().toString();
		dateRetrait = computer.getDateRetrait() == null ? nullString : computer.getDateRetrait().toString();

		logger.debug("Passage d'un Computer vers sont equivalent réduit ComputerDTO");

		return (new ComputerDTO(String.valueOf(computer.getId()), computer.getName(), dateSortie, dateRetrait,
				computer.getCompany().getName()));
	}

	public static List<CompanyDTO> mapCompanyToCompanyDTO(List<Company> companies) {

		List<CompanyDTO> listeCompaniesDto = new ArrayList<CompanyDTO>();
		for (Company company : companies) {
			listeCompaniesDto.add(new CompanyDTO(company.getId(), company.getName()));
		}
		logger.debug("Passage d'une liste de Company vers sont equivalent réduit CompanyDTO");
		return listeCompaniesDto;
	}
}
