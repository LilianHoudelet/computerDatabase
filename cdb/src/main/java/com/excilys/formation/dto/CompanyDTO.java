package com.excilys.formation.dto;

public class CompanyDTO {
	int id;
	String nom;
	
	public CompanyDTO(int id, String nom) {
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
