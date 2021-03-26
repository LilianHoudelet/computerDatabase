package com.excilys.formation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.service.ComputerSuppressionService;

/**
 * Servlet implementation class DeleteComuterServlet
 */
@WebServlet("/DeleteComputerServlet")
public class DeleteComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
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
					ComputerSuppressionService.supprDataOrdiId(Integer.parseInt(id));
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
