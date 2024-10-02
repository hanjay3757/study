package Calculator; // 이 서블릿이 속한 패키지 선언

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CalculatorServlet") // URL 패턴을 지정
public class CalculatorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2022736461224913869L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// x 값을 3000으로 고정
		int x = 3000;
		// 사용자가 입력한 y 값을 가져와 정수로 변환
		int y = Integer.parseInt(request.getParameter("y"));
		// x와 y의 곱을 계산
		int result = x * y;

		// 결과를 세 자리마다 쉼표를 찍어서 포맷팅
		String formattedResult = String.format("%,d", result);

		// 세션을 통해 결과를 저장
		HttpSession session = request.getSession();
		session.setAttribute("result", formattedResult); // 결과를 세션에 저장

		// 계산 결과를 표시할 JSP 페이지로 포워드
		request.getRequestDispatcher("calculator.jsp").forward(request, response);
	}
}
