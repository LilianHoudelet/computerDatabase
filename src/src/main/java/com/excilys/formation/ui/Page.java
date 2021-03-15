package src.main.java.com.excilys.formation.ui;

import java.util.List;

import src.main.java.com.excilys.formation.model.Computer;

public class Page {
	public static final String BARRE = "+---------------------------------------------+";
	
	public static void printComputer(List<Computer> infos) {
	
		if (infos.isEmpty()) {
			System.out.println(BARRE);
			System.out.println(" Pas d'information trouvees");
		} else {
			for (Computer cpu : infos) {
				System.out.println(BARRE);
				System.out.println("|  " + cpu.toString());  
			}
		}
		System.out.println(BARRE);
	}
}
