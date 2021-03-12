package src.main.java.com.excilys.formation.model;

import java.time.LocalDate;

public class Computer {
	int id;
	String nom;
	LocalDate dateSortie; 
	LocalDate dateRetrait;
	Company company;
	
	public Computer(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public Computer(int id, String nom, LocalDate dateSortie, LocalDate dateRetrait, Company company) {
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		this.dateRetrait = dateRetrait;
		this.company = company;
		
	}
	public Computer(int id, String nom, LocalDate dateSortie, Company company) {
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		//this.dateRetrait = dateRetrait;
		this.company = company;
		
	}
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public LocalDate getDateSortie() {
		return this.dateSortie;
	}
	
	public LocalDate getDateRetrait() {
		return this.dateRetrait;
	}
	
	public Company getCompany() {
		return this.company;
	}
	
	@Override
	public String toString() {
		return "  " + this.id + ".  " + this.nom;
	}
}
