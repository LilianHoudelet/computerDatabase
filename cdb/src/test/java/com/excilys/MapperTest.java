package com.excilys;

import java.time.LocalDate;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MapperTest {
	
	
	public static Test computerToComputerDTOTest() {
		Computer computer = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),new Company(1,"Apple Inc."));
		
		ComputerDTO computerDTO = DtoMapper. mapComputerToComputerDTOOne(computer);
		
		return null;
		
	}

}
