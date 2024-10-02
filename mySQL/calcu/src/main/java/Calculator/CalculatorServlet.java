package Calculator; // 이 줄을 추가하세요

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2022736461224913869L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// x 값을 3000으로 고정
		int x = 3000;
		// y 값을 사용자 입력으로 받음
		int y = Integer.parseInt(request.getParameter("y"));
		// 곱셈 계산
		int result = x * y;

		// 결과를 세 자리마다 쉼표를 찍어서 포맷팅
		String formattedResult = String.format("%,d", result);

		// 결과를 JSP에 전달
		request.setAttribute("result", formattedResult);
		// JSP 페이지로 포워드
		request.getRequestDispatcher("/calculator.jsp").forward(request, response);
	}
}
