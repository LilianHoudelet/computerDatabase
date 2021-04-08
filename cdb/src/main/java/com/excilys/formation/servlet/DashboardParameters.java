package com.excilys.formation.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.ComputerPage;

@Component
@SessionScope
public class DashboardParameters {
	
	private ComputerPage listPage;
	private List<ComputerDTO> listComputers = new ArrayList<>();
	private ModelAndView modelAndView;
	
	public DashboardParameters() {
		modelAndView = new ModelAndView("dashboard");
		this.listPage = new ComputerPage();
	}
	
	public void setPageIndex(int pageIndex) {
		listPage.setNumPage(pageIndex);
	}

	public void setNumberOfValues(int numberOfValues) {
		listPage.setNbEltsParPage(numberOfValues);
	}

	public void setSearchValue(String search) {
		listPage.setSearchString(search);
	}
	
	public void setValues(List<ComputerDTO> listComputer) {
		listPage.setComputerList(listComputer);
	}
	public void setMaxComputers(int value) {
		listPage.setNbElts(value);
	}
	public void setOrderByValue(String orderBy) {
		listPage.setSearchString(orderBy);
	}
	public ComputerPage getPage() {
		return listPage;
	}

	public String getSearchValue() {
		return listPage.getSearchString();
	}
	
	public ModelAndView getModelAndView() {
		modelAndView.addObject("computerNumber", listPage.getNbElts());
		modelAndView.addObject("search", listPage.getSearchString());
		modelAndView.addObject("page", listPage.getNumPage());
		modelAndView.addObject("index", listPage.getIndex());
		modelAndView.addObject("computerList", listComputers);
		modelAndView.addObject("maxPage", listPage.getMaxPage());
		return modelAndView;
	}

	
}
