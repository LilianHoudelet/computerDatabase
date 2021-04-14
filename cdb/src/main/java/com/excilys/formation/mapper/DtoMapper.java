package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.CompanyPersist;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DtoMapper {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(DtoMapper.class);

	public List<ComputerDTO> mapComputerToComputerDTO(List<Computer> computers) {

		List<ComputerDTO> listeComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {

			listeComputerDto.add(mapComputerToComputerDTOOne(computer));
		}
		logger.debug("Passage d'une liste de Computer vers sont equivalent réduit ComputerDTO");
		return listeComputerDto;
	}

	public ComputerDTO mapComputerToComputerDTOOne(Computer computer) {
		String nullString = "Unknown";

		String dateSortie;
		String dateRetrait;

		dateSortie = computer.getDateSortie() == null ? nullString : computer.getDateSortie().toString();
		dateRetrait = computer.getDateRetrait() == null ? nullString : computer.getDateRetrait().toString();

		logger.debug("Passage d'un Computer vers sont equivalent réduit ComputerDTO");

		return (new ComputerDTO(String.valueOf(computer.getId()), computer.getName(), dateSortie, dateRetrait,
				computer.getCompany().getName()));
	}

	public List<CompanyDTO> mapCompanyToCompanyDTO(List<Company> companies) {

		List<CompanyDTO> listeCompaniesDto = new ArrayList<CompanyDTO>();
		for (Company company : companies) {
			listeCompaniesDto.add(new CompanyDTO(company.getId(), company.getName()));
		}
		logger.debug("Passage d'une liste de Company vers sont equivalent réduit CompanyDTO");
		return listeCompaniesDto;
	}
	
	public List<Company> mapCompanyPersistToCompanyList(List<CompanyPersist> companyList) {
		List<Company> list = new ArrayList<Company>();
		for(CompanyPersist c : companyList) {
			list.add(mapCompanyPersistToCompany(c));
		}
		return list;
	}
	
	public Company mapCompanyPersistToCompany(CompanyPersist company) {
		return new Company(company.getId(), company.getName());		
	}
}
