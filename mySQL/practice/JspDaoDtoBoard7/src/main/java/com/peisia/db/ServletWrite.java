package com.peisia.db;

import java.io.IOException;

import javax.servlet.RequestDispatcher; // 추가된 부분
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletWrite")
public class ServletWrite extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8907413264731166991L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청 파라미터로 DTO 객체 생성
		Dto dto = new Dto(request.getParameter("title"), request.getParameter("id"), request.getParameter("text"));

		// DAO 객체 생성
		Dao dao = new Dao();

		// 데이터베이스에 DTO 기록
		dao.write(dto);

		// list.jsp로 포워딩 (URL 변경 없음)
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp"); // 추가된 부분
		dispatcher.forward(request, response); // 변경된 부분
	}
}
