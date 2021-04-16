package com.excilys.formation.dto.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyPersist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
//	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "companyId")
//	private Set<ComputerPersist> computerList = new HashSet<>(0);

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

//	public Set<ComputerPersist> getComputerList() {
//		return computerList;
//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
//
//	public void setComputerList(Set<ComputerPersist> computerList) {
//		this.computerList = computerList;
//	}
}
