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
import javax.servlet.http.HttpSession;

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
	
	public static final String SORTED = "sorted";
	public static final String NOT_SORTED = "notSorted";
	
	public static final String FILTER = "search";
	
	private static final long serialVersionUID = 1L;
	
       
    public ComputerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pagination = 1;
		int nbEltParPage = 10;
		String chaineFiltre = "";
		boolean sorted = false;
		
		int nombreElements = 0;
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		
		ComputerPage computerPage = new ComputerPage();
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute(NUM_PAGE) == null) {	
			session.setAttribute(NUM_PAGE, 1);
		} else {
			pagination = (int) session.getAttribute(NUM_PAGE);
		}
		
		if (session.getAttribute(NOMBRE_ELEMENTS) == null) {
			session.setAttribute(NOMBRE_ELEMENTS, 10);
		} else {
			nbEltParPage = (int) session.getAttribute(NOMBRE_ELEMENTS);
		}
		
		if (session.getAttribute(FILTER) == null) {
			session.setAttribute(FILTER, "");
		} else {
			chaineFiltre = (String) session.getAttribute(FILTER);
		}
		
		if (session.getAttribute(SORTED) == null) {
			session.setAttribute(SORTED, false);
		} else {
			sorted = (boolean) session.getAttribute(SORTED);
		}
					
		String nbEltsParPageString = request.getParameter(NOMBRE_ELEMENTS);
		try {
			nbEltParPage = Integer.parseInt(nbEltsParPageString);
		} catch (Exception e) {
		}
		
		String SortedString = request.getParameter(SORTED);
		
		try {
			if(SortedString != null && Boolean.parseBoolean(SortedString) != sorted) {
				sorted = !sorted;
			}
		} catch (Exception e) {
		}
		
		String numPageString = request.getParameter(NUM_PAGE);		
		try {
			pagination = Integer.parseInt(numPageString);
		} catch (Exception e) {
			pagination = 1;
		}

		String filterString = request.getParameter(FILTER);
		if (filterString != null && !filterString.equals(chaineFiltre)) {
			chaineFiltre = filterString;
			sorted = false;
		}
		
		session.setAttribute(NUM_PAGE, pagination);
		session.setAttribute(NOMBRE_ELEMENTS, nbEltParPage);
		session.setAttribute(FILTER, chaineFiltre);
		session.setAttribute(SORTED, sorted);
		
		
		try {
			if (sorted) {
				computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdiPageFiltreTrie(nbEltParPage, pagination-1, chaineFiltre));
			} else {
				computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdiPageFiltre(nbEltParPage, pagination-1, chaineFiltre));
			}
			nombreElements = ComputerDataService.recupDataOrdiNombre(chaineFiltre);
			
			computerPage = new ComputerPage(nbEltParPage, nombreElements , computers);
			computerPage.setNumPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute(FILTER, chaineFiltre);
		request.setAttribute(SORTED, !sorted);
		request.setAttribute(COMPUTER_NUMBER, nombreElements);
		request.setAttribute(LISTE_COMPUTER, computerPage.getComputerList());
		request.setAttribute(NUM_PAGE, pagination);
		request.setAttribute(PAGE_INDEX, computerPage.getIndex());
		request.setAttribute(MAX_PAGE, computerPage.getMaxPage());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				
		doGet(request, response);
	}
	
}
