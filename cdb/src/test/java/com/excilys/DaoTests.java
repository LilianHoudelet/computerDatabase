package com.excilys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyDataService;
import com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.service.ComputerDetailsDataService;


public class DaoTests {
	
	CompanyDataService companyService;
	ComputerDataService computerService;
	ComputerDetailsDataService computerDetailsService;
	
	@Test
	public void daoCompanyTest() throws Exception {
		assertEquals(24,companyService.recupDataCompanyId("Nintendo").getId());
		assertNotEquals(2,companyService.recupDataCompanyId("Apple Inc.").getId());
		
		assertEquals(0,companyService.recupDataCompanyId("Test").getId());
		assertEquals(companyService.recupDataCompany().size(), 42);
	}
	
	@Test
	public void daoComputerTest() throws Exception {
		String chaine = "DEC";
		
		assertEquals(computerService.recupDataOrdiNombre(""),computerService.recupDataOrdi().size());
		assertEquals(computerDetailsService.recupDataDetailsOrdi("Wii"),new Computer(229,"Wii",LocalDate.parse("2006-11-19"),null, new Company(24,"Nintendo")));
		assertEquals(computerDetailsService.recupDataDetailsOrdi(229),new Computer(229,"Wii",LocalDate.parse("2006-11-19"),null, new Company(24,"Nintendo")));
		assertEquals(computerService.recupDataOrdiNombre(chaine),computerService.recupDataOrdiPageFiltreTrie(500,0,chaine, chaine, false).size()); // 14 entrées acutuellement
	}	
}
