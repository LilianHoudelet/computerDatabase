package com.excilys.formation.dto;

public class ComputerDTO {
	String id;
	String nom;
	String dateSortie; 
	String dateRetrait;
	String companyName;
	
	public ComputerDTO() {
		
	}
	
	public ComputerDTO(String id, String nom, String dateSortie, String dateRetrait, String companyName) {
		this.id = id;
		this.nom = nom;
		this.dateSortie = dateSortie;
		this.dateRetrait = dateRetrait;
		this.companyName = companyName;
	}

	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public String getDateSortie() {
		return this.dateSortie;
	}
	
	public String getDateRetrait() {
		return this.dateRetrait;
	}
	
	public String getCompany() {
		return this.companyName;
	}
}
