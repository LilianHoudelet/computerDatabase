package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	
	public static final String LISTE_COMPANIES = "CompanyList";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
		
		try {
			companies = DtoMapper.mapCompanyToCompanyDTO(CompanyDataService.recupDataCompany());
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
			if (ValidationComputer.isComputerValid(computerName, dateSortie, dateRetrait)) {
				Computer addedComputer = MapStringToComputer.ComputerStringToComputer(computerName, dateSortie, dateRetrait, company);
				AjoutOrdinateurService.ajoutDataService(addedComputer);
			} else {
				System.out.println("Ordinateur non valide");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/cdb/ComputerServlet");
	}

}
