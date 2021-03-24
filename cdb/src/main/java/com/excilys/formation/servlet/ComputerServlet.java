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
	
	public static final String SORTED = "sorted";
	public static final String NOT_SORTED = "notSorted";
	
	private static final long serialVersionUID = 1L;
	
	private static int nbEltParPage = 10;
	
	private static int page = 1;
	
	private static int sorted = 0;
	private static int notSorted = 1;
	
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
		
		String SortedString = request.getParameter(SORTED);
		
		try {
			if( sorted != Integer.parseInt(SortedString)) {
				notSorted = sorted;
				sorted = Integer.parseInt(SortedString);
			}
			
		} catch (Exception e) {
		}
 
		String numPageString = request.getParameter(NUM_PAGE);
				
		try {
			page = Integer.parseInt(numPageString);
		} catch (Exception e) {
			page = 1;
		}
		
		try {
			if (sorted == 0) {
				computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdiPageTrie(nbEltParPage, page-1));
			} else {
				computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdiPage(nbEltParPage, page-1));
			}
			nombre = ComputerDataService.recupDataOrdiNombre();
			
			computerPage = new ComputerPage(nbEltParPage, nombre , computers);
			computerPage.setNumPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute(SORTED, sorted);
		request.setAttribute(NOT_SORTED, notSorted);
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
