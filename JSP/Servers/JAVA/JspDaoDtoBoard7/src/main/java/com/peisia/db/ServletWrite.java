package com.peisia.db;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletWrite")
public class ServletWrite extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8907413264731166991L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");
		Dto dto = new Dto(request.getParameter("title"), id, request.getParameter("text"));
		Dao dao = new Dao();
		dao.write(dto);
		response.sendRedirect("list.jsp");
	}
}