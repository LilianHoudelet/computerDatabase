package src.main.java.com.excilys.formation.model;

import src.main.java.com.excilys.formation.mapper.AjoutDatabase;
import src.main.java.com.excilys.formation.mapper.ChercherDetails;
import src.main.java.com.excilys.formation.mapper.CompanyInfos;
import src.main.java.com.excilys.formation.mapper.ComputerInfos;
import src.main.java.com.excilys.formation.ui.Menu;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
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
	
	/**
	 * Appelle les différentes fonctions pour afficher le menu, récupérer les données,
	 * appelle les fonctions de recherche 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void menu1() throws ClassNotFoundException, SQLException {
		int entreeMenu1;
		
		
		Menu.afficherMenu1();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu1 = reader.nextInt();
		
		
		switch(entreeMenu1) {	
			case(AFFICHER_INFOS_ORDINATEURS) :
			{
				List<Computer> infos = ComputerInfos.computerInformations();
				Menu.printComputer(infos);
				// autre page, autre action -> menu 2
				menu2();
				break;
			}
			
			case(AFFICHER_INFOS_CONSTRUCTEURS) :
			{
				List<Company> infos = CompanyInfos.companyInformations();
				Menu.printCompany(infos);
				break;
			}
			
			case(AJOUTER_INFORMATIONS) :
			{
				String patternDate = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
				LocalDate dateSortie = null;
				LocalDate dateRetrait = null;
				
				Menu.demandeEntreeNom();
				Scanner readerLine = new Scanner(System.in);
				String nomMachine = readerLine.nextLine();
				
				Menu.demandeEntreeConstructeur();
				String nomConstructeur = readerLine.nextLine();
				
				Menu.demandeEntreeDateSortie();
				String dateSortieString = readerLine.nextLine();
				
				Menu.demandeEntreeDateRetrait();
				String dateRetraitString = readerLine.nextLine();
				
				
				if(Pattern.matches(patternDate, dateSortieString)) {
					try {
						dateSortie = LocalDate.parse(dateSortieString);
					}
					catch(Exception e){
						System.out.println("Invalid Date");
					}
				}
				
				
				if(Pattern.matches(patternDate, dateRetraitString)) {
					try {
						dateRetrait = LocalDate.parse(dateRetraitString);
					}
					catch(Exception e){
						System.out.println("Invalid Date");
					}
				}
				AjoutDatabase.ajoutDB(new Computer(0, nomMachine, dateSortie, dateRetrait, nomConstructeur)); 
				System.out.println("Ajoute");
				break;
			}
			default : {
				
			}
		}
	}
	
	public static void menu2() throws ClassNotFoundException, SQLException {
		int entreeMenu2;
		
		Menu.afficherMenu2();
		Menu.demandeEntreeMenu();
		Scanner reader = new Scanner(System.in);
		entreeMenu2 = reader.nextInt();
	
		
		switch(entreeMenu2) {	
			case(1) :
			{
				// mettre a jour infos
				break;
			}
			
			case(2) :
			{
				Menu.demandeEntreeNom();
				Scanner readerLine = new Scanner(System.in);
				String machine = readerLine.nextLine();
					
				Computer details = ChercherDetails.details(machine);
				Menu.afficheDetails(details);
				

				break;
			}
			
			case(3) :
			{
				// supprimer d'une table
				break;
			}
			default : {
				
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		menu1();
	}
}
