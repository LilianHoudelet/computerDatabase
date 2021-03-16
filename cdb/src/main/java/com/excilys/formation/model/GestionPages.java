package com.excilys.formation.model;

import java.util.List;
import java.util.Scanner;

import com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.ui.Page;

public class GestionPages {
	
	public static void affichePage() throws Exception {
		int taillePage = 10;
		
		int pageMax = ComputerDataService.recupDataOrdiNombre() / taillePage ;
		
		int page = 0;
		
		String entreeMenu;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		
		do {
			List<Computer> infos = ComputerDataService.recupDataOrdiPage(taillePage, page);
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
