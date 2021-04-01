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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.mapper.MapStringToComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyDataService;
import com.excilys.formation.service.ComputerDetailsDataService;
import com.excilys.formation.service.UpdateDatabaseService;
import com.excilys.formation.service.ValidationComputer;

/**
 * Servlet implementation class EditComputerServlet
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	
	
	public static final String LISTE_COMPANIES = "CompanyList";
	public static final String INTRODUCED = "IntroducedDate";
	public static final String DISCONTINUED = "DiscontinuedDate";
	public static final String COMPUTER_NAME = "ComputerName";
	public static final String COMPUTER_ID = "ComputerId";
	public static final String COMPANY_NAME = "CompanyName";
	public static final String COMPUTER_ENTER = "id";
		

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerDetailsDataService computerDetails;
	@Autowired
	private CompanyDataService companyService;
	@Autowired
	private UpdateDatabaseService updateComputerService;
	@Autowired
	private ValidationComputer validator;
	@Autowired
	private DtoMapper dtoMapper;
	@Autowired
	private MapStringToComputer mapStringToComputer;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerToUpdate = null;
		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
		
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter(COMPUTER_ENTER));
		
		session.setAttribute(COMPUTER_ENTER, id);
		try {
			computerToUpdate = dtoMapper.mapComputerToComputerDTOOne(computerDetails.recupDataDetailsOrdi(id));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			companies = dtoMapper.mapCompanyToCompanyDTO(companyService.recupDataCompany());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute(COMPUTER_ID, id);
		
		request.setAttribute(COMPUTER_NAME,computerToUpdate.getName());
		request.setAttribute(INTRODUCED,computerToUpdate.getDateSortie());
		request.setAttribute(DISCONTINUED,computerToUpdate.getDateRetrait());
		request.setAttribute(COMPANY_NAME,computerToUpdate.getCompany());
		
		request.setAttribute(LISTE_COMPANIES, companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int id = (int) session.getAttribute(COMPUTER_ENTER);
		
		String computerName = request.getParameter("ComputerName");
		String dateSortie = request.getParameter("ComputerDateSortie");
		String dateRetrait = request.getParameter("ComputerDateRetrait");
		String company = request.getParameter("CompanyId");
		
		try {
			if (validator.isComputerValid(computerName, dateSortie, dateRetrait)) {
				Computer updatedComputer = mapStringToComputer.ComputerStringToComputer(computerName, dateSortie, dateRetrait, company);
				updatedComputer.setId(id);
				
				updateComputerService.updateDataService(updatedComputer);
				
			} else {
				System.out.println("Ordinateur non valide");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/cdb/ComputerServlet");
	}

}
