package src.main.java.com.excilys.formation.model;

import src.main.java.com.excilys.formation.mapper.AjoutDatabase;
import src.main.java.com.excilys.formation.mapper.ChercherDetails;
import src.main.java.com.excilys.formation.mapper.CompanyInfos;
import src.main.java.com.excilys.formation.mapper.ComputerInfos;
import src.main.java.com.excilys.formation.mapper.SupprDatabase;
import src.main.java.com.excilys.formation.mapper.UpdateDatabase;
import src.main.java.com.excilys.formation.service.CheckDate;
import src.main.java.com.excilys.formation.ui.Menu;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


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
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void menu1() throws ClassNotFoundException, SQLException {
		int entreeMenu1;

		Menu.afficherMenu1();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu1 = reader.nextInt();
		// TODO : Gerer les entrees de type string

		Scanner readerLine = new Scanner(System.in);

		switch (entreeMenu1) {
		case (AFFICHER_INFOS_ORDINATEURS): 
			List<Computer> infos = ComputerInfos.computerInformations();
			Menu.printComputer(infos);
			// autre page, autre action -> menu 2
			menu2();
			break;
		
		case (AFFICHER_INFOS_CONSTRUCTEURS): 
			List<Company> infos1 = CompanyInfos.companyInformations();
			Menu.printCompany(infos1);
			break;
		
		case (AJOUTER_INFORMATIONS): 
			

			Menu.demandeEntreeNom();

			String nomMachine = readerLine.nextLine();

			Menu.demandeEntreeConstructeur();
			String nomConstructeur = readerLine.nextLine();

			Menu.demandeEntreeDateSortie();
			String dateSortieString = readerLine.nextLine();

			Menu.demandeEntreeDateRetrait();
			String dateRetraitString = readerLine.nextLine();

			LocalDate dateSortie = CheckDate.dateValide(dateSortieString);
			LocalDate dateRetrait = CheckDate.dateValide(dateRetraitString);

			if (dateRetrait != null && dateSortie != null &&  dateSortie.compareTo(dateRetrait) > 0) { // si on a deux dates et la date de sortie est avant
				Menu.avertissementDate();
			} else { 
				AjoutDatabase.ajoutDB(new Computer(0, nomMachine, dateSortie, dateRetrait, nomConstructeur));
			}
			break;
		
		default: 
			reader.close();
			readerLine.close();
	
		}
	}

	public static void menu2() throws ClassNotFoundException, SQLException {
		int entreeMenu2;

		Menu.afficherMenu2();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu2 = reader.nextInt();

		Scanner readerLine = new Scanner(System.in);

		switch (entreeMenu2) {
		case (METTRE_A_JOUR_INFOS_ORDINATEURS): 

			Menu.demandeEntreeNom();
			// Scanner readerLine = new Scanner(System.in);
			String nomMachine = readerLine.nextLine();

			Computer details = ChercherDetails.details(nomMachine);

			Menu.afficheDetails(details);

			if (details != null) {
				System.out.println("Rentrer les nouvelles informations");
				Menu.demandeEntreeConstructeur();
				String nomConstructeur = readerLine.nextLine();

				Menu.demandeEntreeDateSortie();
				String dateSortieString = readerLine.nextLine();

				Menu.demandeEntreeDateRetrait();
				String dateRetraitString = readerLine.nextLine();

				LocalDate dateSortie = CheckDate.dateValide(dateSortieString);
				LocalDate dateRetrait = CheckDate.dateValide(dateRetraitString);

				UpdateDatabase.modifDB(details,
						new Computer(details.getId(), nomMachine, dateSortie, dateRetrait, nomConstructeur));
			}
			break;

		case (AFFICHER_DETAILS_ORDINATEUR): 
			Menu.demandeEntreeNom();
			// Scanner readerLine = new Scanner(System.in);
			String nomMachineDetails = readerLine.nextLine();
			Computer detailsOrdinateur = ChercherDetails.details(nomMachineDetails);
			Menu.afficheDetails(detailsOrdinateur);

			break;
		
		case (SUPPRIMER_INFORMATIONS): 
			Menu.demandeEntreeNom();
			// Scanner readerLine = new Scanner(System.in);
			String nomMachineInfos = readerLine.nextLine();
			Menu.avertissementSuppression(nomMachineInfos);
			String confirmation = readerLine.nextLine();
			if (!confirmation.isEmpty()
					&& (confirmation.equals(new String("O")) || confirmation.equals(new String("o")))) {
				SupprDatabase.supprimerInfos(nomMachineInfos);
			} else {
				System.out.println("Element non supprime");
			}
			break;
		
		default: 
			reader.close();
			readerLine.close();
		}
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		menu1();
	}
}
