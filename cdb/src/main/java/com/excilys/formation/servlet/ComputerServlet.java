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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.model.ComputerPage;
import com.excilys.formation.service.ComputerDataService;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@WebServlet("/ComputerServlet")
public class ComputerServlet extends HttpServlet {
	
	public static final String COMPUTER_NUMBER = "computerNumber";
	
	public static final String LISTE_COMPUTER = "ComputerList";
	public static final String NOMBRE_ELEMENTS = "nbEltsParPage";
	
	public static final String NUM_PAGE = "page";
	public static final String PAGE_INDEX = "index";
	public static final String MAX_PAGE = "maxPage";
	
	public static final String SORTED_ON = "sortedOn";
	public static final String ASC_DESC = "sortingOrder";
	
	public static final String FILTER = "search";
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(ComputerServlet.class);
	
	@Autowired
	private ComputerDataService computerService;
	@Autowired
	private DtoMapper dtoMapper;
       
//	public ComputerServlet(ComputerDataService computerService, DtoMapper dtoMapper) {
//		this.computerService = computerService;
//		this.dtoMapper = dtoMapper;
//	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int nombreElements = 0;
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		
		ComputerPage computerPage = new ComputerPage();
		
		HttpSession session = request.getSession();
	
		int pagination = initPagination(request);
		int nbEltParPage = initNbElementsParPage(request);
		String chaineFiltre = initChaineFiltre(request);
		String sortedOn = initOrder(request);
		boolean ascendance = initAscendance(request);
						
		nbEltParPage = setNombreEltsParPage(request, nbEltParPage);
		pagination = setPage(request, pagination);
		
		String filterString = request.getParameter(FILTER);
		if (filterString != null && !filterString.equals(chaineFiltre)) {
			chaineFiltre = filterString;
			sortedOn = "computerId";
		}
		
		String sortingString = request.getParameter(SORTED_ON);
		if (sortingString != null && !sortingString.equals(sortedOn)) {
			sortedOn = sortingString;
			ascendance = true;
		} else if (sortingString != null && sortingString.equals(sortedOn)){
			ascendance = !ascendance;
		}
		
		session.setAttribute(NUM_PAGE, pagination);
		session.setAttribute(NOMBRE_ELEMENTS, nbEltParPage);
		session.setAttribute(FILTER, chaineFiltre);
		session.setAttribute(SORTED_ON, sortedOn);
		session.setAttribute(ASC_DESC, ascendance);
		
		try {
			computers = dtoMapper.mapComputerToComputerDTO(computerService.recupDataOrdiPageFiltreTrie(nbEltParPage, pagination-1, chaineFiltre, sortedOn, ascendance));
			
			nombreElements = computerService.recupDataOrdiNombre(chaineFiltre);
			
			computerPage = new ComputerPage(nbEltParPage, nombreElements);
			computerPage.setNumPage(pagination);
		} catch (Exception e) {
			logger.error("La recherche de la page à raté",e);
		}

		request.setAttribute(FILTER, chaineFiltre);
		request.setAttribute(COMPUTER_NUMBER, nombreElements);
		request.setAttribute(LISTE_COMPUTER, computers); // envoi de la liste des computers
		request.setAttribute(NUM_PAGE, pagination);
		request.setAttribute(PAGE_INDEX, computerPage.getIndex());
		request.setAttribute(MAX_PAGE, computerPage.getMaxPage());
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		doGet(request, response);
	}
	
	//##################################################################################################//
	
	private int initPagination(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(NUM_PAGE) == null) {	
			session.setAttribute(NUM_PAGE, 1);
		} else {
			return (int) session.getAttribute(NUM_PAGE);
		}
		return 1;	
	}
	
	private int initNbElementsParPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(NOMBRE_ELEMENTS) == null) {
			session.setAttribute(NOMBRE_ELEMENTS, 10);
		} else {
			return (int) session.getAttribute(NOMBRE_ELEMENTS);
		}
		return 10;
	}
	
	private String initChaineFiltre(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(FILTER) == null) {
			session.setAttribute(FILTER, "");
		} else {
			return (String) session.getAttribute(FILTER);
		}
		return "";
	}
	
	private String initOrder(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(SORTED_ON) == null) {
			session.setAttribute(SORTED_ON, "id");
		} else {
			return (String) session.getAttribute(SORTED_ON);
		}
		
		return "id";
	}
	
	private boolean initAscendance(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute(ASC_DESC) == null) {
			session.setAttribute(ASC_DESC, true);
		} else {
			return (boolean) session.getAttribute(ASC_DESC);
		}
		
		return true;
	}
	
	//##################################################################################################//
	
	private int setNombreEltsParPage(HttpServletRequest request, int nbEltsParPage) {
		
		String nbEltsParPageString = request.getParameter(NOMBRE_ELEMENTS);
		try {
			return Integer.parseInt(nbEltsParPageString);
		} catch (Exception e) {
			return nbEltsParPage;
		}
	}
	
	private int setPage(HttpServletRequest request, int pagination) {
		
		String numPageString = request.getParameter(NUM_PAGE);		
		try {
			return Integer.parseInt(numPageString);
		} catch (Exception e) {
			return 1;
		}
	}
}
