package src.main.java.com.excilys.formation.model;

public class Computer {
	int id;
	String nom;
	
	public Computer(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.nom;
	}
}
