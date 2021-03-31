package com.excilys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.mapper.RequestFilterString;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ValidationComputer;


public class MapperTest {
	
	@Test
	public void computerToComputerDTOTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));
		ComputerDTO computerTest = new ComputerDTO("1","Test", LocalDate.parse("2020-01-09").toString(), LocalDate.parse("2020-10-01").toString(), "Apple Inc.");
		ComputerDTO computerDTO = DtoMapper.mapComputerToComputerDTOOne(computer);
		
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
		
		
		
		assertTrue(computersDTO.equals(DtoMapper.mapComputerToComputerDTO(computers)));
	}
	
	@Test
	public void StringToComputerTest() throws Exception {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),new Company(1,""));	
		Computer computerString = MapStringToComputer.ComputerStringToComputer("Test", "2020-01-09", "2020-10-01", "1");
				
		assertTrue(computer.equals(computerString));
	}
	
	@Test
	public void computerValidTestString() {
		assertTrue(ValidationComputer.isComputerValid("Test","2020-09-01","2020-10-01"));
	}
	
	@Test
	public void computerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));		
		assertTrue(ValidationComputer.isComputerValid(computer));
	}
	
	@Test
	public void computerNotValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-08-01"),new Company(1,"Apple Inc."));		
		assertFalse(ValidationComputer.isComputerValid(computer));
	}
	
	@Test
	public void isComputerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), null,new Company(1,"Apple Inc."));			
		assertTrue(ValidationComputer.isComputerValid(computer));
	}
	
	@Test
	public void mapperFilterTest() {	
		assertEquals(RequestFilterString.convertOrderbool(true)," ASC ");
		assertNotEquals(RequestFilterString.convertOrderbool(true)," DESC ");
	}
	
	@Test
	public void mapperFilterTestCol() {	
		assertEquals(RequestFilterString.convertOrderString("introduced"),"introduced");
		assertEquals(RequestFilterString.convertOrderString("discontinued"),"discontinued");
		assertEquals(RequestFilterString.convertOrderString("computerName"),"computer.name");
		assertEquals(RequestFilterString.convertOrderString("company"),"company.name");
		assertEquals(RequestFilterString.convertOrderString(""),"computer.id");
		assertEquals(RequestFilterString.convertOrderString(null),"computer.id");
	}
	
	

}
