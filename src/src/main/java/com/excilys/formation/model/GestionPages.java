package src.main.java.com.excilys.formation.model;

import java.util.List;
import java.util.Scanner;

import src.main.java.com.excilys.formation.service.ComputerDataService;
import src.main.java.com.excilys.formation.ui.Menu;

public class GestionPages {
	
	public static void affichePage() throws Exception {
		int page = 0;
		int taillePage = 10;
		String entreeMenu;
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
				if (page <= 0) {
					page = 1;
				}
			} else {
				break;
			}
			
		} while (true);
		
		reader.close();
	}
}
