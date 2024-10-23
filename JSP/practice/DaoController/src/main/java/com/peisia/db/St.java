package com.peisia.db;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peisia.c.util.Cw;

@WebServlet("/x/*")
public class St extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1047381054333765205L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		Cw.wn("뭐찍히나 보자:" + action);
	}
}