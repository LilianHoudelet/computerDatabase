package com.excilys.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.AddComputerDTO;
import com.excilys.formation.dto.CompanyDTO;

@Component
@SessionScope
public class AddComputerParameters {
	private ModelAndView modelAndView;
	private AddComputerDTO computer;
	private List<CompanyDTO> companyList;
	
	public AddComputerParameters() {
		this.modelAndView = new ModelAndView("addComputer");
		this.computer = null;
		this.companyList = new ArrayList<>();
	}
	
	public AddComputerDTO getComputer() {
		return computer;
	}
	
	public void setComputer(AddComputerDTO computer) {
		this.computer = computer;
	}
	
	public List<CompanyDTO> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyDTO> companyList) {
		this.companyList = companyList;
	}

	public ModelAndView getModelAndView() {
		modelAndView.addObject("companyList", companyList);
		modelAndView.addObject("AddComputerDTO", new AddComputerDTO());
		return modelAndView;
	}
	
}
