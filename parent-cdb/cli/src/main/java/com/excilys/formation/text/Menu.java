package com.excilys.formation.text;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class Menu {
	
	public static final String BARRE = "+---------------------------------------------+";
	
	public static void afficherMenu1() {
		System.out.println(BARRE);
		System.out.println("|           1. Afficher ordinateurs           |");
		System.out.println(BARRE);
		System.out.println("|          2. Afficher constructeurs          |");
		System.out.println(BARRE);
		System.out.println("|           3. Ajouter informations           |");
		System.out.println(BARRE);
		System.out.println("|          4. Supprimer constructeur          |");
		System.out.println(BARRE);
		System.out.println("|                 5. Quitter                  |");
		System.out.println(BARRE);
	}
	
	public static void afficherMenu2() {
		System.out.println(BARRE);
		System.out.println("|               1. Mettre a jour              |");
		System.out.println(BARRE);
		System.out.println("|              2. Plus de details             |");
		System.out.println(BARRE);
		System.out.println("|          3. Supprimer informations          |");
		System.out.println(BARRE);
		System.out.println("|                 4. Quitter                  |");
		System.out.println(BARRE);
	}
	
	public static void demandeEntreeMenu() {
		System.out.println("");
		System.out.println("Entrez la valeur de l'action correspondante : ");
		System.out.print(">> ");
	}
	
	public static void demandeEntreeNom() {
		System.out.println("");
		System.out.println("Entrez le nom de la machine : ");
		System.out.print(">> ");
	}
	
	public static void demandeEntreeDateSortie() {
		System.out.println("");
		System.out.println("Entrez le nom de la date de sortie sous le format YYYY-MM-JJ : ");
		System.out.print(">> ");
	}
	
	public static void demandeEntreeDateRetrait() {
		System.out.println("");
		System.out.println("Entrez le nom de la date de retrait sous le format YYYY-MM-JJ : ");
		System.out.print(">> ");
	}
	
	public static void demandeEntreeConstructeur() {
		System.out.println("");
		System.out.println("Entrez le nom du constructeur : ");
		System.out.print(">> ");	
	}
	
	public static void avertissementSuppression(String elementSupprime) {
		System.out.println("");
		System.out.println("Vous allez supprimer " + elementSupprime + " de la base de donnee : ");
		System.out.println("Veuillez confirmer (O/N) ");
		System.out.print(">> ");
	}
	
	public static void avertissementDate() {
		System.out.println("");
		System.out.println(BARRE);
		System.out.println("  Vous avez rentré une date de Sortie après la date de retrait de la vente");
		System.out.println(BARRE);
	}
	
	public static void printComputer(List<Computer> infos) {
		
		if (infos.isEmpty()) {
			System.out.println(BARRE);
			System.out.println(" Pas d'information trouvees");
		} else {
			for (Computer cpu : infos) {
				System.out.println(BARRE);
				System.out.println("  " + cpu.toString());  
			}
		}
		System.out.println(BARRE);
	}
	
	public static void printCompany(List<Company> infos) {
		for (Company company : infos) {
			System.out.println(BARRE);
			System.out.println("" + company.getId() + "  " + company.getName());  
		}
		System.out.println(BARRE);
	}
	
	public static void afficheDetails(Computer cp) {
		
		System.out.println(BARRE);
		
		if (cp == null) {
			System.out.println("  Aucun ordinateur a ete trouve");
		} else {
			System.out.println("| Nom             : " + cp.getName()); 
				
			if (cp.getCompany() == null) {
				System.out.println("| Constructeur    : unknown");	
			} else {
				System.out.println("| Constructeur    : " + cp.getCompany().toString());
			}
				//" \n| Date de Sortie  : " + cp.getDateSortie().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			if (cp.getDateSortie() == null) {
				System.out.println("| Date de Sortie  : unknown");
			} else {
				System.out.println("| Date de Sortie  : " + cp.getDateSortie().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			}
			if (cp.getDateRetrait() == null) {
				System.out.println("| Date de Retrait : unknown");
			} else {
				System.out.println("| Date de Retrait : " + cp.getDateRetrait().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			}
		}
		System.out.println(BARRE);
	}
}
