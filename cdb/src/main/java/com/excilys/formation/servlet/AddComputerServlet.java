package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.AjoutOrdinateurService;
import com.excilys.formation.service.CompanyDataService;
import com.excilys.formation.service.ValidationComputer;

/**
 * Servlet implementation class AddComputerServlet
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	


	public static final String LISTE_COMPANIES = "CompanyList";
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AjoutOrdinateurService computerService;
	
	@Autowired
	private CompanyDataService companyService;
	
	@Autowired
	private ValidationComputer validator;
	
	@Autowired
	private DtoMapper dtoMapper;
	
	@Autowired
	private MapStringToComputer mapStringToComputer;
       
//	public AddComputerServlet(AjoutOrdinateurService computerService, CompanyDataService companyService,
//			ValidationComputer validator, DtoMapper dtoMapper, MapStringToComputer mapStringToComputer) {
//		this.computerService = computerService;
//		this.companyService = companyService;
//		this.validator = validator;
//		this.dtoMapper = dtoMapper;
//		this.mapStringToComputer = mapStringToComputer;
//	}
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
		
		try {
			companies = dtoMapper.mapCompanyToCompanyDTO(companyService.recupDataCompany());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		request.setAttribute(LISTE_COMPANIES, companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String computerName = request.getParameter("ComputerName");
		String dateSortie = request.getParameter("ComputerDateSortie");
		String dateRetrait = request.getParameter("ComputerDateRetrait");
		String company = request.getParameter("CompanyId");
		
		try {
			if (validator.isComputerValid(computerName, dateSortie, dateRetrait)) {
				Computer addedComputer = mapStringToComputer.ComputerStringToComputer(computerName, dateSortie, dateRetrait, company);
				computerService.ajoutDataService(addedComputer);
			} else {
				System.out.println("Ordinateur non valide");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/cdb/ComputerServlet");
	}

}
