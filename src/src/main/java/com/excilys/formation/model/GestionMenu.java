package src.main.java.com.excilys.formation.model;

import src.main.java.com.excilys.formation.mapper.CompanyInfos;
import src.main.java.com.excilys.formation.mapper.ComputerInfos;
import src.main.java.com.excilys.formation.ui.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author excilys
 *
 */
public class GestionMenu {
	static int entreeMenu;
	boolean exit = true;
	
	/**
	 * Appelle les différentes fonctions pour afficher le menu, récupérer les données,
	 * appelle les fonctions de recherche 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void menu1() throws ClassNotFoundException, SQLException {
		Menu.afficherMenu1();
		Menu.demandeEntree();
		Scanner reader = new Scanner(System.in);
		entreeMenu = reader.nextInt();
		reader.close();
		
		switch(entreeMenu) {	
			case(1) :
			{
				List<Computer> infos = ComputerInfos.computerInformations();
				Menu.printComputer(infos);
				break;
			}
			
			case(2) :
			{
				List<Company> infos = CompanyInfos.companyInformations();
				Menu.printCompany(infos);
				break;
			}
			
			case(3) :
			{
				// Ajout d'une information
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
