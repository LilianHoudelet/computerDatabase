package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.mapper.DtoMapper;
import com.excilys.formation.service.ComputerDataService;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/ComputerServlet")
public class ComputerServlet extends HttpServlet {
	
	public static final String COMPUTER_NUMBER = "computerNumber";
	public static final String COMPUTER_NAME = "computerName";
	public static final String COMPUTER_ID = "computerID";
	public static final String COMPUTER_DISCONTINUED = "computerDiscontinued";
	public static final String COMPUTER_INTRODUCED = "computerIntroduced";
	public static final String COMPANY_NAME = "companyName";
	
	public static final String LISTE_COMPUTER = "ComputerList";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int nombre = 0;
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		
		try {
			computers = DtoMapper.mapComputerToComputerDTO(ComputerDataService.recupDataOrdi());
			nombre = ComputerDataService.recupDataOrdiNombre();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute(COMPUTER_NUMBER, nombre);
		request.setAttribute(LISTE_COMPUTER, computers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
