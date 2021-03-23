package com.excilys;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
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
	public void computerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));
				
		assertTrue(ValidationComputer.isComputerValid(computer));
	}
	
	@Test
	public void computerNotValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), LocalDate.parse("2020-08-01"),new Company(1,"Apple Inc."));
				
		assertTrue(!ValidationComputer.isComputerValid(computer));
	}
	
	@Test
	public void isComputerValidTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-09-01"), null,new Company(1,"Apple Inc."));
				
		assertTrue(ValidationComputer.isComputerValid(computer));
	}

}
