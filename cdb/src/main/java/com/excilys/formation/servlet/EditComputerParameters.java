package com.excilys.formation.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;

@Component
@SessionScope
public class EditComputerParameters {
	
	private ModelAndView modelAndView;
	private ComputerDTO computer;
	private List<CompanyDTO> companyList;
	
	public EditComputerParameters() {
		this.modelAndView = new ModelAndView("addComputer");
		this.computer = null;
		this.companyList = new ArrayList<>();
	}
	
	public ComputerDTO getComputer() {
		return computer;
	}
	
	public void setComputer(ComputerDTO computer) {
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
		// ADD des infos du Computer
		return modelAndView;
	}
}
