package com.excilys.formation.model;

import java.time.LocalDate;

public class Computer {
	int id;
	String nom;
	LocalDate dateSortie; 
	LocalDate dateRetrait;
	Company company;
	
	public Computer() {
		
	}
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
	
	public class ComputerBuilder {
		int id;
		String nom;
		LocalDate dateSortie; 
		LocalDate dateRetrait;
		Company company;
		
		ComputerBuilder withId(int id) {
			this.id = id;
			return this;
		}
		ComputerBuilder withName(String name) {
			this.nom = name;
			return this;
		}
		ComputerBuilder withDateSortie(LocalDate dateSortie) {
			this.dateSortie = dateSortie;
			return this;
		}
		ComputerBuilder withDateRetrait(LocalDate dateRetrait) {
			this.dateRetrait = dateRetrait;
			return this;
		}
		ComputerBuilder withCompany(Company company) {
			this.company = company;
			return this;
		}
		Computer build() {
			return new Computer();
			
		}
	}
}
