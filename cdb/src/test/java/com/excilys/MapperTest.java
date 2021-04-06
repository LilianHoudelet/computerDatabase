package com.excilys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.mapper.RequestFilterString;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ValidationComputer;


public class MapperTest {
	
	ValidationComputer validator;
	DtoMapper dtoMapper;
	RequestFilterString requestFilterString;
	MapStringToComputer mapStringToComputer;
	
	@Test
	public void computerToComputerDTOTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));
		ComputerDTO computerTest = new ComputerDTO("1","Test", LocalDate.parse("2020-01-09").toString(), LocalDate.parse("2020-10-01").toString(), "Apple Inc.");
		ComputerDTO computerDTO = dtoMapper.mapComputerToComputerDTOOne(computer);
		
		assertTrue(computerDTO.equals(computerTest));
	}
	
	@Test
	public void computerListToComputerDTOTestList() {
		Computer computer1 = new Computer(1, "Test", LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"), new Company(1,"Apple Inc."));
		Computer computer2 = new Computer(2, "Test2", null, null,new Company(2,"Testy"));
		
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer1);
		computers.add(computer2);
		
		ComputerDTO computerTest1 = new ComputerDTO("1","Test", LocalDate.parse("2020-01-09").toString(), LocalDate.parse("2020-10-01").toString(), "Apple Inc.");
		ComputerDTO computerTest2 = new ComputerDTO("2","Test2", "Unknown", "Unknown", "Testy");
		
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		computersDTO.add(computerTest1);
		computersDTO.add(computerTest2);
		
		
		
		assertTrue(computersDTO.equals(dtoMapper.mapComputerToComputerDTO(computers)));
	}
	
	@Test
	public void companyListToCompanyDTOTestList() {
		Company company1 = new Company(1,"Test1");
		Company company2 = new Company(2,"Test2");
		
		List<Company> companies = new ArrayList<Company>();
		companies.add(company1);
		companies.add(company2);
		
		CompanyDTO companydto1 = new CompanyDTO(1,"Test1");
		CompanyDTO companydto2 = new CompanyDTO(2,"Test2");
		
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		companiesDTO.add(companydto1);
		companiesDTO.add(companydto2);
		
		assertTrue(companiesDTO.equals(dtoMapper.mapCompanyToCompanyDTO(companies)));
	}
	
	@Test
	public void StringToComputerTest() throws Exception {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),new Company(1,""));	
		Computer computerString = mapStringToComputer.ComputerStringToComputer("Test", "2020-01-09", "2020-10-01", "1");
				
		assertTrue(computer.equals(computerString));
	}
	
	@Test
	public void computerValidTestString() {
		assertTrue(validator.isComputerValid("Test","2020-09-01","2020-10-01"));
	}
	
	@Test
	public void computerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));		
		assertTrue(validator.isComputerValid(computer));
	}
	
	@Test
	public void computerNotValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-08-01"),new Company(1,"Apple Inc."));		
		assertFalse(validator.isComputerValid(computer));
	}
	
	@Test
	public void isComputerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), null,new Company(1,"Apple Inc."));			
		assertTrue(validator.isComputerValid(computer));
	}
	
	@Test
	public void mapperFilterTest() {	
		assertEquals(requestFilterString.convertOrderbool(true)," ASC ");
		assertNotEquals(requestFilterString.convertOrderbool(true)," DESC ");
	}
	
	@Test
	public void mapperFilterTestCol() {	
		assertEquals(requestFilterString.convertOrderString("introduced"),"introduced");
		assertEquals(requestFilterString.convertOrderString("discontinued"),"discontinued");
		assertEquals(requestFilterString.convertOrderString("computerName"),"computer.name");
		assertEquals(requestFilterString.convertOrderString("company"),"company.name");
		assertEquals(requestFilterString.convertOrderString(""),"computer.id");
		assertEquals(requestFilterString.convertOrderString(null),"computer.id");
	}
	
	

}
