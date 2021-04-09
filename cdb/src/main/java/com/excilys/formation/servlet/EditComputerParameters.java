package com.excilys.formation.servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.AddComputerDTO;
import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;

@Component
@SessionScope
public class EditComputerParameters {
	
	private ModelAndView modelAndView;
	private ComputerDTO computer;
	private List<CompanyDTO> companyList;
	
	public EditComputerParameters() {
		this.modelAndView = new ModelAndView("editComputer");
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
		modelAndView.addObject("computerId", computer.getId());
		modelAndView.addObject("computerName", computer.getName());
		modelAndView.addObject("introducedDate", computer.getDateSortie());
		modelAndView.addObject("discontinuedDate", computer.getDateRetrait());
		modelAndView.addObject("companyName", computer.getCompany());
		modelAndView.addObject("AddComputerDTO", new AddComputerDTO());
		return modelAndView;
	}
}
