package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.ComputerPage;
import com.excilys.formation.service.ComputerDataService;

@WebServlet("/ComputerServlet")
public class ComputerServlet extends HttpServlet {
	
	public static final String COMPUTER_NUMBER = "computerNumber";
	
	public static final String LISTE_COMPUTER = "ComputerList";
	public static final String NOMBRE_ELEMENTS = "nbEltsParPage";
	
	public static final String NUM_PAGE = "page";
	public static final String PAGE_INDEX = "index";
	public static final String MAX_PAGE = "maxPage";
	
	private static final long serialVersionUID = 1L;
	
	private static int nbEltParPage = 10;
	
	private static int page = 1;
	
	private ComputerPage computerPage;
       
    public ComputerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nombre = 0;
		
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();	
		
		computerPage = new ComputerPage(nbEltParPage, nombre , computers);
		// TODO ajouter des sessions
				
		String nbEltsParPageString = request.getParameter(NOMBRE_ELEMENTS);
				
		try {
			nbEltParPage = Integer.parseInt(nbEltsParPageString);
		} catch (Exception e) {
			
		}
 
		String numPageString = request.getParameter(NUM_PAGE);
				
		try {
			page = Integer.parseInt(numPageString);
		} catch (Exception e) {
			page = 1;

		}
		
		try {
			computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdiPage(nbEltParPage, page-1));
			nombre = ComputerDataService.recupDataOrdiNombre();
			
			computerPage = new ComputerPage(nbEltParPage, nombre , computers);
			computerPage.setNumPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		request.setAttribute(COMPUTER_NUMBER, nombre);
		request.setAttribute(LISTE_COMPUTER, computerPage.getComputerList());
		request.setAttribute(NUM_PAGE, page);
		request.setAttribute(PAGE_INDEX, computerPage.getIndex());
		request.setAttribute(MAX_PAGE, computerPage.getMaxPage());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("cb");
		System.out.println(id);
		
		doGet(request, response);
	}

}
