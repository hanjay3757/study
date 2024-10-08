package com.peisia.db;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peisia.c.util.Cw;

@WebServlet("/Board/*")
public class ServletController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -58314103384972813116L;
	String nextPage;
	Dao dao;

	@Override
	public void init() throws ServletException {
		dao = new Dao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		Cw.wn("action:" + action);
		if (action != null) {
			switch (action) {

			// 지우기
			case "/del": // 했음
				Cw.wn("삭제");
				nextPage = "/list.jsp";
				dao.del(request.getParameter("no"));
				break;

			// 글쓰기

			case "/write": // 했음
				Cw.wn("쓰기");
				nextPage = "/list.jsp";
				Dto dto = new Dto(request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
				dao.write(dto);
				break;

			// 수정 사항

			case "/edit_insert":// todo
				Cw.wn("수정-insert");
				break;
			case "/edit_proc":// todo
				Cw.wn("수정-proc");
//				nextPage="/list.jsp";
//				dao.edit(
//						new Dto(
//								request.getParameter("title"),
//								request.getParameter("text")
//								)
//						,request.getParameter("no")
//						);				
				break;
//			case "/read":// todo
//				Cw.wn("읽기");
//				nextPage = "/read.jsp";
//				Dto d = dao.read(request.getParameter("no"));
//				request.setAttribute("post", d);
//				break;
//			case "/list":// todo
//				Cw.wn("리스트");
//				nextPage = "/list.jsp";
//				break;
			}
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
		}
	}
}