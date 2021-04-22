package com.excilys.formation.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.controller.AddComputerParameters;
import com.excilys.formation.controller.DashboardParameters;
import com.excilys.formation.controller.EditComputerParameters;
import com.excilys.formation.dto.AddComputerDTO;
import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.services.AjoutOrdinateurService;
import com.excilys.formation.services.CompanyDataService;
import com.excilys.formation.services.ComputerDataService;
import com.excilys.formation.services.ComputerDetailsDataService;
import com.excilys.formation.services.ComputerSuppressionService;
import com.excilys.formation.services.UpdateDatabaseService;
import com.excilys.formation.validation.DateConstraintValidator;

@RestController
public class ControllerRest {

	ComputerDataService computerService;
	ComputerDetailsDataService computerDetailService;
	CompanyDataService companyService;
	UpdateDatabaseService updateDatabaseService;
	ComputerSuppressionService supprComputerService;

	AjoutOrdinateurService addComputerService;

	DashboardParameters dashboardParameters;
	AddComputerParameters addComputerParameters;
	EditComputerParameters editComputerParameters;
	DateConstraintValidator dateConstraintValidator;

	DtoMapper dtoMapper;
	MapStringToComputer mapStringToComputer;

	public ControllerRest(ComputerDataService computerDataService, ComputerDetailsDataService computerDetailDataService,
			CompanyDataService companyDataService, UpdateDatabaseService updateDatabaseService,
			AjoutOrdinateurService ajoutOrdinateurService, ComputerSuppressionService computerSuppressionService,
			DashboardParameters dashboardParameters,
			AddComputerParameters addComputerParameters, EditComputerParameters editComputerParameters,
			DateConstraintValidator dateConstraintValidator, DtoMapper dtoMapper,
			MapStringToComputer mapStringToComputer) {
		this.computerService = computerDataService;
		this.computerDetailService = computerDetailDataService;
		this.updateDatabaseService = updateDatabaseService;
		this.addComputerService = ajoutOrdinateurService;
		this.supprComputerService = computerSuppressionService;
		this.dashboardParameters = dashboardParameters;
		this.addComputerParameters = addComputerParameters;
		this.editComputerParameters = editComputerParameters;
		this.dtoMapper = dtoMapper;
		this.companyService = companyDataService;
		this.dateConstraintValidator = dateConstraintValidator;
		this.mapStringToComputer = mapStringToComputer;
	}

	@GetMapping("/computer")
	public List<Computer> getComputer(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer nbEltsParPage, @RequestParam(required = false) String search,
			@RequestParam(required = false) String sortedOn) {

		readParameters(page, nbEltsParPage, search, sortedOn);

		getDashboardValues();

		return computerService.recupDataOrdiPageFiltreTrie(dashboardParameters.getPage());
	}

	private void readParameters(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer nbEltsParPage, @RequestParam(required = false) String search,
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

	private void getDashboardValues() {

		dashboardParameters.setMaxComputers(computerService.recupDataOrdiNombre(dashboardParameters.getSearchValue()));
		dashboardParameters.setValues(dtoMapper
				.mapComputerToComputerDTO(computerService.recupDataOrdiPageFiltreTrie(dashboardParameters.getPage())));
	}
	
	@PostMapping("/computer")
	public void dashboardPost(@RequestBody(required = false) String selection) {
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
	}

	@GetMapping("/computerUpdate")
	public ComputerDTO getComputerId(@RequestParam(required = true) Integer id) throws Exception {
		ComputerDTO computer = dtoMapper.mapComputerToComputerDTOOne(computerDetailService.recupDataDetailsOrdi(id));
		editComputerParameters.setComputer(computer);

		return editComputerParameters.getComputer();
	}

	@GetMapping("/computerUpdate/companyList")
	public List<CompanyDTO> getCompanyList() throws Exception {

		editComputerParameters.setCompanyList(dtoMapper.mapCompanyToCompanyDTO(companyService.recupDataCompany()));

		return editComputerParameters.getCompanyList();
	}

	@PostMapping("/computerUpdate")
	public void postComputerUpdate(@RequestBody AddComputerDTO computer, BindingResult r) {
		dateConstraintValidator.validate(computer, r);
		if (r.hasErrors()) {
		} else {
			Computer addedComputer = mapStringToComputer.ComputerStringToComputer(computer);
			updateDatabaseService.updateDataService(addedComputer);
		}
	}

	@PostMapping("/addingComputer")
	public void addComputerPost(@RequestBody AddComputerDTO computer, BindingResult r) {
		dateConstraintValidator.validate(computer, r);
		if (r.hasErrors()) {
			
		} else {
			Computer addedComputer = mapStringToComputer.ComputerStringToComputer(computer);
			addComputerService.ajoutDataService(addedComputer);
		}
	}
}
