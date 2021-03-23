package com.excilys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.excilys.formation.service.CompanyDataService;
import com.excilys.formation.service.ComputerDataService;


public class DaoTests {
	
	
	@Test
	public void daoCompanyTest() throws Exception {
		assertEquals(24,CompanyDataService.recupDataCompanyId("Nintendo").getId());
		assertNotEquals(2,CompanyDataService.recupDataCompanyId("Apple Inc.").getId());
		
		assertEquals(0,CompanyDataService.recupDataCompanyId("Test").getId());
	}
	
	@Test
	public void daoComputerTest() throws Exception {
		assertEquals(ComputerDataService.recupDataOrdiNombre(),ComputerDataService.recupDataOrdi().size());
	}
		
}
