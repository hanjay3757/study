
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletHelloWorld")

public class session extends HttpServlet {
	/**
	 * 
	 */
//	밑에 꺼 업뎃마다 번호가 바뀌고 암호화에 도움되는 뭐시기한 그거
	private static final long serialVersionUID = 6403644450541216099L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==== 두 겟");
		System.out.println("id: " + req.getParameter("id"));
		System.out.println("pw: " + req.getParameter("pw"));
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}

//		PrintWriter out = response.getWriter();
//		out.println("Hello World !!! ");
//	}}
