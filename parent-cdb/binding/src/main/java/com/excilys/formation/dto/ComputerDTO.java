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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((dateRetrait == null) ? 0 : dateRetrait.hashCode());
		result = prime * result + ((dateSortie == null) ? 0 : dateSortie.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
