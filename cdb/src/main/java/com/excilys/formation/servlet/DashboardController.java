package com.excilys.formation.servlet;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.formation.dto.AddComputerDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.AjoutOrdinateurService;
import com.excilys.formation.service.CompanyDataService;
import com.excilys.formation.service.ComputerDataService;
import com.excilys.formation.service.ComputerDetailsDataService;
import com.excilys.formation.service.ComputerSuppressionService;
import com.excilys.formation.service.UpdateDatabaseService;
import com.excilys.formation.service.ValidationComputer;
import com.excilys.formation.validator.DateConstraintValidator;
//simport com.excilys.formation.validator.DateConstraintValidator;

@Controller
public class DashboardController {
	static Logger logger = org.slf4j.LoggerFactory.getLogger(DashboardController.class);
	
	DashboardParameters dashboardParameters;
	AddComputerParameters addComputerParameters;
	EditComputerParameters editComputerParameters;
	
	ComputerDataService computerService;
	CompanyDataService companyService;
	ComputerSuppressionService supprComputerService;
	AjoutOrdinateurService addComputeService;
	UpdateDatabaseService updateDatabaseService;
	ComputerDetailsDataService computerDetailsService;
	
	ValidationComputer validationComputer;
	DateConstraintValidator dateConstraintValidator;
	
	DtoMapper dtoMapper;
	MapStringToComputer mapStringToComputer;
	
	private DashboardController(DashboardParameters dashboardParameters,
			ComputerDataService computerDataService,
			DtoMapper dtoMapper,
			ComputerSuppressionService supprComputerService,
			AddComputerParameters addComputerParameters,
			CompanyDataService companyDataService,
			AjoutOrdinateurService ajoutOrdinateurService,
			ValidationComputer validationComputer,
			MapStringToComputer mapStringToComputer,
			EditComputerParameters editComputerParameters,
			UpdateDatabaseService updateDatabaseService,
			ComputerDetailsDataService computerDetailsDataService,
			DateConstraintValidator dateConstraintValidator
			) {
		this.dashboardParameters = dashboardParameters;
		this.computerService = computerDataService;
		this.dtoMapper = dtoMapper;
		this.supprComputerService = supprComputerService;
		this.addComputerParameters = addComputerParameters;
		this.companyService = companyDataService;
		this.addComputeService = ajoutOrdinateurService;
		this.validationComputer = validationComputer;
		this.mapStringToComputer = mapStringToComputer;
		this.editComputerParameters = editComputerParameters;
		this.updateDatabaseService = updateDatabaseService;
		this.computerDetailsService = computerDetailsDataService;
		this.dateConstraintValidator = dateConstraintValidator;
	}
		
	@GetMapping("/dashboard")
	public ModelAndView dashboardGet(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer nbEltsParPage, @RequestParam(required = false) String search,
			@RequestParam(required = false) String sortedOn) {
		
		readParameters(page, nbEltsParPage, search, sortedOn);
		
		getDashboardValues();
				
		return dashboardParameters.getModelAndView();
	}
	
	private void getDashboardValues() {
		
		
		dashboardParameters.setMaxComputers(computerService.recupDataOrdiNombre(dashboardParameters.getSearchValue()));
		dashboardParameters.setValues(dtoMapper.mapComputerToComputerDTO(computerService.recupDataOrdiPageFiltreTrie(dashboardParameters.getPage())));
	}

	private void readParameters(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer nbEltsParPage,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) String orderBy) {
		if (page != null) {
			dashboardParameters.setPageIndex(page);
		} else {
			dashboardParameters.setPageIndex(1);
		}
		if (nbEltsParPage != null) {
			dashboardParameters.setNumberOfValues(nbEltsParPage);
		}
		if (search != null) {
			dashboardParameters.setSearchValue(search);
		} else {
			dashboardParameters.setSearchValue(dashboardParameters.getSearchValue());
		}
		if (orderBy != null) {
			dashboardParameters.setOrderByValue(orderBy);
		}
	}
	
	@PostMapping("/dashboard")
	public ModelAndView dashboardPost(@RequestParam(required = false) String selection) {
		
		if (selection != null) {
			List<String> ids = Arrays.asList(selection.split(","));
			for (String id : ids) {
				try {
					supprComputerService.supprDataOrdiId(Integer.parseInt(id));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		getDashboardValues();
		
		return dashboardParameters.getModelAndView();
	}
	
	@GetMapping("/addComputer")
	public ModelAndView addComputerGet() {
		addComputerParameters.setCompanyList(dtoMapper.mapCompanyToCompanyDTO(companyService.recupDataCompany()));
		return addComputerParameters.getModelAndView();
	}
	
	@PostMapping("/addComputer")
	public RedirectView addComputerPost(@Valid @ModelAttribute("AddComputerDTO") AddComputerDTO computer, BindingResult r) {
		dateConstraintValidator.validate(computer,r);
		if (r.hasErrors()) {
			return new RedirectView("addComputer");
		} else {
			Computer addedComputer = mapStringToComputer.ComputerStringToComputer(computer);
			addComputeService.ajoutDataService(addedComputer);
			
			return new RedirectView("dashboard");
		}
	}
	
	@GetMapping("/editComputer")
	public ModelAndView editComputerGet(@RequestParam(required = true) Integer id) {

		ComputerDTO computer = dtoMapper.mapComputerToComputerDTOOne(computerDetailsService.recupDataDetailsOrdi(id));
		editComputerParameters.setComputer(computer);
		
		editComputerParameters.setCompanyList(dtoMapper.mapCompanyToCompanyDTO(companyService.recupDataCompany()));
		
		return editComputerParameters.getModelAndView();
	}
	
	@PostMapping("/editComputer")
	public RedirectView editComputerPost(@Valid @ModelAttribute("AddComputerDTO") AddComputerDTO computer, BindingResult r) {
		dateConstraintValidator.validate(computer,r);
		if (r.hasErrors()) {
			return new RedirectView("editComputer");
		} else {
		computer.setId(editComputerParameters.getComputer().getId());
		Computer addedComputer = mapStringToComputer.ComputerStringToComputer(computer);
		
		updateDatabaseService.updateDataService(addedComputer);
		return new RedirectView("dashboard");
		}
	}
		
}
