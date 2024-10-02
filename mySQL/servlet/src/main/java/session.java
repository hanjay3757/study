
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
//	private static final long serialVersionUID = 6403644450541216099L;는 Java에서 Serializable 인터페이스 구현할 때 쓰는 고유 식별자임. 객체 직렬화랑 역직렬화할 때 버전 관리 용도임.
//
//	용도:
//	버전 관리: 클래스 바뀌면 이 값 바꿔서 호환성 문제 피함. 직렬화된 객체가 클래스 버전 안 맞으면 오류 발생함.
//
//	안정성: serialVersionUID 명시하면 JVM이 직렬화된 객체랑 클래스가 일치하는지 확인해서 데이터 손상이나 오류 예방함.
//
//	디버깅 도움: 클래스 변경 사항 추적하기 쉬워서 개발하는 데 도움 됨.
//
//	결론적으로, serialVersionUID는 객체 직렬화 관련해서 중요한 역할 함. 객체 안정성과 호환성 유지하는 데 도움 줌.
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
