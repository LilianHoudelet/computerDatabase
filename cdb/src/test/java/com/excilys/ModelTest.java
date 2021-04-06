package com.excilys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CheckDate;

public class ModelTest {
	
	CheckDate checkDate;
		
	@Test
	public void computerGetterTest() {
		Company company = new Company(1,"CompanyNameTest");
		Computer computerTest = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		Computer computerTestCompanyNull = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),null);
		
		assertEquals(1,computerTest.getId());
		assertEquals("Test",computerTest.getName());
		assertEquals(new Company(1,"CompanyNameTest"),computerTest.getCompany());
		assertEquals(computerTest.getDateSortie(),LocalDate.parse("2020-01-09"));
		assertEquals(computerTest.getDateRetrait(),LocalDate.parse("2020-10-01"));
				
		assertNotEquals(2,computerTest.getId());
		assertNotEquals("Tester",computerTest.getName());
		assertNotEquals(new Company(2,""),computerTest.getCompany());
		
		assertEquals("  1.  Test",computerTest.toString());
		
		assertEquals(computerTest,computerTest);
		assertEquals(computerTest,new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company));
		assertNotEquals(computerTest,company);
		assertNotEquals(computerTest,null);
		assertNotEquals(computerTest,computerTestCompanyNull);
		assertNotEquals(computerTestCompanyNull,computerTest);	
	}
	
	@Test
	public void computerModelTestDateVide() {
		Company company = new Company(1,"CompanyNameTest");
		Computer computerTest = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		Computer computerTestSecond = new Computer(1,"Test",null, LocalDate.parse("2020-10-01"),company);
		
		assertNotEquals(computerTest,computerTestSecond);
		assertNotEquals(computerTestSecond,computerTest);
		
		Computer computerTestThird = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		Computer computerTestForth = new Computer(1,"Test",LocalDate.parse("2020-01-09"),null,company);
		
		assertNotEquals(computerTestThird,computerTestForth);
		assertNotEquals(computerTestForth,computerTestThird);		
	}
	
	@Test
	public void computerModelTestNomVide() {
		Company company = new Company(1,"CompanyNameTest");
		Computer computerTest = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		Computer computerTestNameNull = new Computer(1,null,LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		
		assertNotEquals(computerTest,computerTestNameNull);
		assertNotEquals(computerTestNameNull,computerTest);
			
	}
	
	@Test
	public void companyTest() {
		Company company = new Company(1,"CompanyNameTest");
		Computer computerTest = new Computer(1,"Test",LocalDate.parse("2020-01-09"), LocalDate.parse("2020-10-01"),company);
		
		assertEquals(company,company);
		assertNotEquals(company,null);
		assertNotEquals(company,computerTest);
		assertEquals(32,company.hashCode());
		assertNotEquals(33,company.hashCode());
		
		assertEquals(company.toString(),"CompanyNameTest");
		assertEquals(1,company.getId());
		assertEquals(company.getName(),"CompanyNameTest");
	}
	
	@Test
	public void date() throws Exception {
		
		assertEquals(checkDate.dateValide("2010-01-01"),LocalDate.parse("2010-01-01"));
		assertEquals(checkDate.dateValide("aze"),null);
		assertThrows(Exception.class, () -> {checkDate.dateValide("2010-13-13");});
	}
}
