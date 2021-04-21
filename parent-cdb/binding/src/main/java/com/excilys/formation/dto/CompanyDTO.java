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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
