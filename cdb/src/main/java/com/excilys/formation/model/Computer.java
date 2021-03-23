package com.excilys.formation.model;

import java.time.LocalDate;

public class Computer {
	int id;
	String nom;
	LocalDate dateSortie; 
	LocalDate dateRetrait;
	Company company;

	public Computer(int id, String nom, LocalDate dateSortie, LocalDate dateRetrait, Company company) {
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		this.dateRetrait = dateRetrait;
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

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((dateRetrait == null) ? 0 : dateRetrait.hashCode());
		result = prime * result + ((dateSortie == null) ? 0 : dateSortie.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (dateRetrait == null) {
			if (other.dateRetrait != null)
				return false;
		} else if (!dateRetrait.equals(other.dateRetrait))
			return false;
		if (dateSortie == null) {
			if (other.dateSortie != null)
				return false;
		} else if (!dateSortie.equals(other.dateSortie))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
