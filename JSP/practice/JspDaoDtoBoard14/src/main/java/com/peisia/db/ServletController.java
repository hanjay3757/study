package com.peisia.db;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peisia.c.util.Cw;

@WebServlet("/board/*")
public class ServletController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8431029604273162611L;
	String nextPage;
	Dao dao;
	Service service;

	@Override
	public void init() throws ServletException {
		dao = new Dao();
		service = new Service();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		Cw.wn("action:" + action);
		if (action != null) {
			switch (action) {
			case "/del":
				nextPage = "/board/list"; // 컨트롤러 리스트를 타게 수정함
				service.del(request.getParameter("no")); // 🐇서비스🐇:삭제 기능
				break;
			case "/write":
				nextPage = "/board/list"; // 컨트롤러 리스트를 타게 수정함
				Dto dto = new Dto(request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
//				dao.write(dto);				
				service.write(dto); // 🐇서비스🐇:쓰기 기능
				break;
			case "/edit_insert":// todo
				Cw.wn("수정-insert");
				nextPage = "/edit.jsp";
//				request.setAttribute("post", dao.read(request.getParameter("no")));	//🐇서비스🐇:수정 기능					
				request.setAttribute("post", service.read(request.getParameter("no"))); // 🐇서비스🐇:수정 기능
				break;
			case "/edit_proc":// todo
				Cw.wn("수정-proc");
				nextPage = "/board/list"; // 컨트롤러 리스트를 타게 수정함
//				dao.edit(
//						new Dto(
//								request.getParameter("title"),
//								request.getParameter("text")
//								)
//						,request.getParameter("no")
//						);	//🐇서비스🐇:수정 기능				
				service.edit(new Dto(request.getParameter("title"), request.getParameter("text")),
						request.getParameter("no")); // 🐇서비스🐇:수정 기능
				break;
			case "/read":
				nextPage = "/read.jsp";
//				Dto d=dao.read(request.getParameter("no"));
				Dto d = service.read(request.getParameter("no")); // 🐇서비스🐇:읽기 기능
				request.setAttribute("post", d);
				break;
			case "/list":
				nextPage = "/list.jsp";
//				ArrayList<Dto> posts = dao.list();	//🐇서비스🐇:리스트 기능
				ArrayList<Dto> posts = service.list(); // 🐇서비스🐇:리스트 기능
				request.setAttribute("posts", posts);
				break;
			}
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
		}
	}
}