package com.excilys.formation.ui;

import java.util.List;

import com.excilys.formation.model.Computer;

public class Page {
	public static final String BARRE = "+---------------------------------------------+";
	
	public static void printComputerPage(List<Computer> infos, int page) {
	
		if (infos.isEmpty()) {
			System.out.println(BARRE);
			System.out.println(" Pas d'information trouvees");
		} else {
			for (Computer cpu : infos) {
				System.out.println(BARRE);
				System.out.println("   " + cpu.toString());  
			}
		}
		System.out.println("+-------------    Page : " + page +  "    ----------------+");
		
	}
	
	public static void demandeEntreePage() {
		System.out.println("");
		System.out.println("Entrez + pour la page suivante, - pour la page précédente");
		System.out.println("le numéro de la page souhaitée ou rien pour quitter");
		System.out.print(">> ");
	}
}
