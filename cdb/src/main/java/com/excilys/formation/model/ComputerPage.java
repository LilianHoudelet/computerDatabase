package com.excilys.formation.model;

import java.util.List;

import com.excilys.formation.dto.ComputerDTO;

public class ComputerPage {
	
	int numPage;
	int nbEltsParPage;
	int index;
	static int maxPage;
	int nbElts;
	List<ComputerDTO> computerList;
	
	public ComputerPage(int nbEltsParPage, int nbElts, List<ComputerDTO> computerList) {
		super();
		this.nbEltsParPage = nbEltsParPage;
		this.computerList = computerList;
		this.numPage = 1;
		maxPage = (nbElts -1) / this.nbEltsParPage + 1;
		indexPagination();
	}
	
	public ComputerPage() {
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
		indexPagination();
	}

	public int getNbEltsParPage() {
		return nbEltsParPage;
	}

	public void setNbEltsParPage(int nbEltsParPage) {
		this.nbEltsParPage = nbEltsParPage;
		setNumPage(1);
	}

	public int getIndex() {
		return index;
	}

	public List<ComputerDTO> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<ComputerDTO> computerList) {
		this.computerList = computerList;
		setNbEltsParPage(computerList.size());
		maxPage = (computerList.size() -1) / this.nbEltsParPage + 1;
	}
	
	
	public void indexPagination() {
		if (this.numPage <= 3) {
			this.index = 3;
		} else if (this.numPage >= maxPage - 2) {
			this.index = maxPage - 2;
		} else {
			this.index = this.numPage;
		}
	}

	public int getMaxPage() {
		return maxPage;
	}

	public static void setMaxPage(int maxPage) {
		ComputerPage.maxPage = maxPage;
	}

}
