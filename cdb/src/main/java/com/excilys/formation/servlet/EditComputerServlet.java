package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			computerToUpdate = DtoMapper.mapComputerToComputerDTOOne(ComputerDetailsDataService .recupDataDetailsOrdi(id));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			companies = DtoMapper.mapCompanyToCompanyDTO(CompanyDataService.recupDataCompany());
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
			if (ValidationComputer.isComputerValid(computerName, dateSortie, dateRetrait)) {
				Computer updatedComputer = MapStringToComputer.ComputerStringToComputer(computerName, dateSortie, dateRetrait, company);
				updatedComputer.setId(id);
				
				UpdateDatabaseService.updateDataService(updatedComputer);
				
			} else {
				System.out.println("Ordinateur non valide");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/cdb/ComputerServlet");
	}

}
