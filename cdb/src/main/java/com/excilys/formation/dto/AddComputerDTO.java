package com.excilys.formation.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddComputerDTO {
	String id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 1, message = "Name can't be Empty")
	String nom;
	
	String dateSortie; 
	String dateRetrait;
	
	String companyId;

	public AddComputerDTO() {
		
	}
	
	public AddComputerDTO(String id, String nom, String dateSortie, String dateRetrait, String companyId) {
		this.id = id;
		this.nom = nom.trim();
		this.dateSortie = dateSortie;
		this.dateRetrait = dateRetrait;
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}

	public String getNom() {
		return nom;
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

	public void setNom(String nom) {
		this.nom = nom;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (companyId == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyId.equals(other.companyName))
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
