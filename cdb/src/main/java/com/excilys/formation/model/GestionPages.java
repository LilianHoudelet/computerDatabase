package com.excilys.formation.model;

import java.util.List;
import java.util.Scanner;

import com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.ui.Menu;

public class GestionPages {
	
	public static void affichePage() throws Exception {
		int page = 0;
		int taillePage = 10;
		String entreeMenu;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		
		int pageMax = ComputerDataService.recupDataOrdiNombre();
		
		do {
			List<Computer> infos = ComputerDataService.recupDataOrdiPage(taillePage, page);
			Menu.printComputer(infos);
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
				break;
			}
			
		} while (true);
		//reader.close();
	}
}
