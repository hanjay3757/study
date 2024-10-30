package com.peisia.jsp.el.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletEl")
public class ServletEl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7065572009799114389L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cat cat = new Cat("키티", 7);

		request.setAttribute("kitty", cat);

		request.setAttribute("x", "문자엑스");

		RequestDispatcher rd = request.getRequestDispatcher("/cat.jsp");
		rd.forward(request, response);
	}

}