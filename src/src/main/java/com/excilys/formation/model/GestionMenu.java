package src.main.java.com.excilys.formation.model;

import src.main.java.com.excilys.formation.ui.Menu;
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
	 */
	public static void menu1() {
		Menu.afficherMenu1();
		Menu.demandeEntree();
		Scanner reader = new Scanner(System.in);
		entreeMenu = reader.nextInt();
		reader.close();
		
		switch(entreeMenu) {
			case(1) :
			{
				// Affichage de la base de donnee
				// appel avec menu 2
				break;
			}
			case(2) :
			{
				//Affichage des constructeurs d'ordinateurs
				break;
			}
			case(3) :
			{
				// Ajout d'une information
				break;
			}
			default : {
				// 
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		menu1();
	}
}
