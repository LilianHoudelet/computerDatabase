package com.excilys.formation.model;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.ui.Page;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GestionPages {
	
	@Autowired
	private ComputerDataService computerService;
	
	public void affichePage() throws Exception {
		int taillePage = 10;
		
		int pageMax = computerService.recupDataOrdiNombre("") / taillePage ;
		
		int page = 0;
		
		String entreeMenu;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		
		do {
			List<Computer> infos = computerService.recupDataOrdiPage(taillePage, page);
			Page.printComputerPage(infos,page+1);
			Page.demandeEntreePage();
			entreeMenu = reader.nextLine();
			
			if ("+".equals(entreeMenu)) {
				page++;
				if (page > pageMax) {
					page = pageMax;
				}
			} else if ("-".equals(entreeMenu)) {
				page--;
				if (page < 0) {
					page = 0;
				}
			} else {
				try {
					page = Integer.parseInt(entreeMenu);
					if( page  > pageMax) {
						page = pageMax;
					}
					else if (page < 0) {
						page = 0;
					}
				} catch(Exception e) {
					break;
				}
			}
		} while (true);
		//reader.close();
	}
}
