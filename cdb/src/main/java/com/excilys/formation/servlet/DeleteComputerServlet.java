package com.excilys.formation.servlet;

import java.io.IOException;

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

import com.excilys.formation.service.ComputerSuppressionService;

/**
 * Servlet implementation class DeleteComuterServlet
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@WebServlet("/DeleteComputerServlet")
public class DeleteComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerSuppressionService supprComputerService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ids[] = request.getParameter("selection").split(",");

		if (ids == null) {
			System.out.println("None");
		} else {
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
		response.sendRedirect("/cdb/ComputerServlet");
	}

}
