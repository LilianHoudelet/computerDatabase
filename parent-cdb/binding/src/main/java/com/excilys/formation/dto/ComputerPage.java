package com.excilys.formation.dto;

import java.util.List;

public class ComputerPage {
	
	int numPage = 1;
	int nbEltsParPage = 10;
	int index = 3;
	int maxPage;
	int nbElts;
	String orderBy = "id";
	boolean asc = true;
	String searchString = "";
	List<ComputerDTO> computerList;
	
	public ComputerPage(int nbEltsParPage, int nbElts) { 
		super();
		this.nbEltsParPage = nbEltsParPage;
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
		this.maxPage = (nbElts -1) / (this.nbEltsParPage) + 1;
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
	}
	
	public void setNbElts (int nbElts) {
		this.nbElts = nbElts;
		this.maxPage = (nbElts -1) / (this.nbEltsParPage) + 1;
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

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setSearchString(String search) {
		this.searchString = search;		
	}

	public String getSearchString() {
		return this.searchString;
	}

	public int getNbElts() {
		return this.nbElts;
	}

	public void setOrder(String order) {
		if (this.orderBy.equals(order)) {
			this.asc = !this.asc;
		} else {
			this.orderBy = order;
			this.asc = true;
		}
	}
	
	public boolean getAsc() {
		return this.asc;
	}
	
	public String getOrderBy() {
		return this.orderBy;
	}

	@Override
	public String toString() {
		return "ComputerPage [numPage=" + numPage + ", nbEltsParPage=" + nbEltsParPage + ", index=" + index
				+ ", maxPage=" + maxPage + ", nbElts=" + nbElts + ", orderBy=" + orderBy + ", asc=" + asc
				+ ", searchString=" + searchString + ", computerList=" + computerList + "]";
	}
}
