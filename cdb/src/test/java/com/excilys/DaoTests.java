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
	
	
	@Test
	public void daoCompanyTest() throws Exception {
		assertEquals(24,CompanyDataService.recupDataCompanyId("Nintendo").getId());
		assertNotEquals(2,CompanyDataService.recupDataCompanyId("Apple Inc.").getId());
		
		assertEquals(0,CompanyDataService.recupDataCompanyId("Test").getId());
	}
	
	@Test
	public void daoComputerTest() throws Exception {
		String chaine = "DEC";
		
		assertEquals(ComputerDataService.recupDataOrdiNombre(),ComputerDataService.recupDataOrdi().size());
		assertEquals(ComputerDetailsDataService.recupDataDetailsOrdi("Wii"),new Computer(229,"Wii",LocalDate.parse("2006-11-19"),null, new Company(24,"Nintendo")));
		assertEquals(ComputerDetailsDataService.recupDataDetailsOrdi(229),new Computer(229,"Wii",LocalDate.parse("2006-11-19"),null, new Company(24,"Nintendo")));
		assertEquals(ComputerDataService.recupDataOrdiNombre(chaine),ComputerDataService.recupDataOrdiPageFiltre(500,0,chaine).size()); // 14 entrées acutuellement
	}	
}
