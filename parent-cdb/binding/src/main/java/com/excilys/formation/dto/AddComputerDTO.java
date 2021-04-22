package com.excilys.formation.dto;

public class AddComputerDTO {
	String id;
	String name;
	
	String dateSortie; 
	String dateRetrait;
	
	String companyId;

	public AddComputerDTO() {
		
	}
	
	public AddComputerDTO(String id, String name, String dateSortie, String dateRetrait, String companyId) {
		this.id = id;
		this.name = name.trim();
		this.dateSortie = dateSortie;
		this.dateRetrait = dateRetrait;
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDateSortie() {
		return dateSortie;
	}

	public String getDateRetrait() {
		return dateRetrait;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	public void setDateRetrait(String dateRetrait) {
		this.dateRetrait = dateRetrait;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "AddComputerDTO [id=" + id + ", name=" + name + ", dateSortie=" + dateSortie + ", dateRetrait="
				+ dateRetrait + ", companyId=" + companyId + "]";
	}

}
