package src.main.java.com.excilys.formation.ui;

public class Menu {
	
	public static void afficherMenu1() {
		System.out.println("+--------------------------+");
		System.out.println("| 1. Afficher ordinateurs  |");
		System.out.println("+--------------------------+");
		System.out.println("| 2. Ajouter constructeurs |");
		System.out.println("+--------------------------+");
		System.out.println("| 3. Ajouter informations  |");
		System.out.println("+--------------------------+");
		System.out.println("|        4. Quitter        |");
		System.out.println("+--------------------------+");
	}
	
	public static void afficherMenu2() {
		System.out.println("+--------------------------+");
		System.out.println("|     1. Mettre a jour     |");
		System.out.println("+--------------------------+");
		System.out.println("|    2. Plus de details    |");
		System.out.println("+--------------------------+");
		System.out.println("| 3. Supprimer informations|");
		System.out.println("+--------------------------+");
		System.out.println("|        4. Quitter        |");
		System.out.println("+--------------------------+");
	}
	
	public static void demandeEntree() {
		System.out.println("");
		System.out.println("Entrez la valeur de l'action correspondante : ");
		System.out.println("");
	}
}
