package com.excilys.formation.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.service.AjoutOrdinateurService;
import com.excilys.formation.service.CheckDate;
import com.excilys.formation.service.CompanyDataService;
//import main.java.com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.service.ComputerDetailsDataService;
import com.excilys.formation.service.ComputerSuppressionService;
import com.excilys.formation.service.UpdateDatabaseService;
import com.excilys.formation.ui.Menu;


/**
 * 
 * @author excilys
 *
 */
public class GestionMenu {

	boolean exit = true;

	public static final int AFFICHER_INFOS_ORDINATEURS = 1;
	public static final int AFFICHER_INFOS_CONSTRUCTEURS = 2;
	public static final int AJOUTER_INFORMATIONS = 3;

	public static final int METTRE_A_JOUR_INFOS_ORDINATEURS = 1;
	public static final int AFFICHER_DETAILS_ORDINATEUR = 2;
	public static final int SUPPRIMER_INFORMATIONS = 3;
	public static final String PATTERN_DATE = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";

	/**
	 * Appelle les différentes fonctions pour afficher le menu, récupérer les
	 * données, appelle les fonctions de recherche
	 * @throws Exception 
	 */
	public static void menu1() throws Exception {
		int entreeMenu1;

		Menu.afficherMenu1();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu1 = reader.nextInt();
		// TODO : Gerer les entrees de type string
		Scanner readerLine = new Scanner(System.in);

		switch (entreeMenu1) {
		case (AFFICHER_INFOS_ORDINATEURS): 
			
			//List<Computer> infos = ComputerDataService.recupDataOrdi();
			//Menu.printComputer(infos);
			
			GestionPages.affichePage();
			menu2();
			break;
		
		case (AFFICHER_INFOS_CONSTRUCTEURS): 
			List<Company> infosCompany = CompanyDataService.recupDataOrdi();
			Menu.printCompany(infosCompany);
			break;
		
		case (AJOUTER_INFORMATIONS): 
			// Refactor a faire ici
			LocalDate dateSortie = null;
			String dateSortieString;
			LocalDate dateRetrait = null;
			String dateRetraitString;

			Menu.demandeEntreeNom();

			String nomMachine = readerLine.nextLine();

			Menu.demandeEntreeConstructeur();
			String nomConstructeur = readerLine.nextLine();

			do {
				Menu.demandeEntreeDateSortie();
				dateSortieString = readerLine.nextLine();
				dateSortie = CheckDate.dateValide(dateSortieString);
				
			} while (!(dateSortieString.isEmpty() || dateSortie != null));
			
			do {
				Menu.demandeEntreeDateRetrait();
				dateRetraitString = readerLine.nextLine();
				dateRetrait = CheckDate.dateValide(dateRetraitString);
				
			} while (!(dateRetraitString.isEmpty() || dateRetrait != null));

			if (dateRetrait != null && dateSortie != null &&  dateSortie.compareTo(dateRetrait) > 0) { // si on a deux dates et la date de sortie est avant
				Menu.avertissementDate();
			} else { 
				Company company = CompanyDataService.recupDataOrdiId(nomConstructeur);
		
				AjoutOrdinateurService.ajoutDataService(new Computer(0, nomMachine, dateSortie, dateRetrait, company));
			}
			break;
		
		default: 
			reader.close();
			readerLine.close();
		}
	}

	public static void menu2() throws Exception {
		int entreeMenu2;

		Menu.afficherMenu2();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu2 = reader.nextInt();

		Scanner readerLine = new Scanner(System.in);

		switch (entreeMenu2) {
		case (METTRE_A_JOUR_INFOS_ORDINATEURS): 

			Menu.demandeEntreeNom();
			
			String nomMachine = readerLine.nextLine();

			Computer details = ComputerDetailsDataService.recupDataDetailsOrdi(nomMachine);
			
			Menu.afficheDetails(details);

			if (details != null) {
				System.out.println("Rentrer les nouvelles informations");
				Menu.demandeEntreeConstructeur();
				String nomConstructeur = readerLine.nextLine();
				
				LocalDate dateSortie = null;
				String dateSortieString;
				LocalDate dateRetrait = null;
				String dateRetraitString;
				
				do {
					Menu.demandeEntreeDateSortie();
					dateSortieString = readerLine.nextLine();
					dateSortie = CheckDate.dateValide(dateSortieString);
					if (dateSortieString.isEmpty()) {
						dateSortie = details.getDateSortie();
					}
				} while (!(dateSortieString.isEmpty() || dateSortie != null));
				
				do {
					Menu.demandeEntreeDateRetrait();
					dateRetraitString = readerLine.nextLine();
					dateRetrait = CheckDate.dateValide(dateRetraitString);
					if (dateRetraitString.isEmpty()) {
						dateRetrait = details.getDateRetrait();
					}
				} while (!(dateRetraitString.isEmpty() || dateRetrait != null));
				
				Company company = CompanyDataService.recupDataOrdiId(nomConstructeur);
				
				UpdateDatabaseService.updateDataService(
						new Computer(details.getId(), details.getName(), dateSortie, dateRetrait, company));
				
			}
			break;

		case (AFFICHER_DETAILS_ORDINATEUR): 
			Menu.demandeEntreeNom();
			// Scanner readerLine = new Scanner(System.in);
			String nomMachineDetails = readerLine.nextLine();
			Computer detailsOrdinateur = ComputerDetailsDataService.recupDataDetailsOrdi(nomMachineDetails);
			Menu.afficheDetails(detailsOrdinateur);

			break;
		
		case (SUPPRIMER_INFORMATIONS): 
			Menu.demandeEntreeNom();
			// Scanner readerLine = new Scanner(System.in);
			String nomMachineInfos = readerLine.nextLine();
			Menu.avertissementSuppression(nomMachineInfos);
			String confirmation = readerLine.nextLine();
			
			Computer ordiSuppr = ComputerDetailsDataService.recupDataDetailsOrdi(nomMachineInfos);
			
			if (!confirmation.isEmpty()
					&& (confirmation.equals(new String("O")) || confirmation.equals(new String("o")))) {
				ComputerSuppressionService.supprDataOrdi(ordiSuppr);
			} else {
				System.out.println("Element non supprime");
			}
			break;
		
		default: 
			reader.close();
			readerLine.close();
		}
		
	}

	public static void main(String[] args) throws Exception {
		menu1();
	}
}
