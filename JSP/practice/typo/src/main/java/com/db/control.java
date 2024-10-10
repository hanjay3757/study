package com.db;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peisia.c.util.Cw;

@WebServlet("/Board/*") // URL 패턴 매핑 - "/Board/*" 로 요청이 들어오면 이 서블릿이 처리함
public class control extends HttpServlet {
	private static final long serialVersionUID = -5831410334972813116L; // 클래스의 버전 관리용 고유 ID

	String nextPage; // 다음 페이지 이동을 위한 변수
	Dao dao; // DB와 연동을 위한 Dao 객체

	// 서블릿 초기화 메소드 - 서블릿이 처음 로딩될 때 호출됨
	@Override
	public void init() throws ServletException {
		dao = new Dao(); // Dao 객체 초기화
	}

	// HTTP GET 요청 처리 메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getPathInfo(); // URL 패턴에서 액션 부분(예: /del, /write 등)을 추출
		Cw.wn("action:" + action); // 로깅을 위한 출력

		if (action != null) { // 액션이 null이 아니면 아래 switch문으로 들어감
			switch (action) {

			// 1. 글 삭제 처리
			case "/del": // /Board/del 요청이 들어오면 실행
				Cw.wn("삭제"); // 삭제 작업 시작 로그
				nextPage = "/list.jsp"; // 삭제 후 이동할 페이지 설정 (게시글 목록 페이지)
				dao.del(request.getParameter("no")); // 요청 파라미터로 받은 'no'를 기준으로 DB에서 삭제 처리
				break;

			// 2. 글쓰기 처리
			case "/write": // /Board/write 요청이 들어오면 실행
				Cw.wn("쓰기"); // 글쓰기 작업 시작 로그
				nextPage = "/list.jsp"; // 글 작성 후 이동할 페이지 설정 (게시글 목록 페이지)
				// 사용자가 입력한 제목, 아이디, 텍스트를 Dto 객체로 묶어서 DB에 저장
				Dto dto = new Dto(request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
				dao.write(dto); // 글쓰기 DB 처리
				break;

			// 3. 글 수정 처리 (첫 번째 단계)
			case "/edit_insert": // /Board/edit_insert 요청이 들어오면 실행
				Cw.wn("수정-insert"); // 수정 작업 준비 단계 시작 로그
				// 실제 구현 코드 필요
				break;

			// 4. 글 수정 처리 (두 번째 단계)
			case "/edit_proc": // /Board/edit_proc 요청이 들어오면 실행
				Cw.wn("수정-proc"); // 수정 처리 작업 로그
				// 요청 파라미터로 받은 제목, 텍스트를 Dto 객체로 묶고, 수정할 글의 번호를 받아서 DB 수정
				// 다음 페이지 설정 부분 주석처리됨 (필요시 추가 구현)
				break;

			// 그 외에 추가적인 액션들 (예: 읽기, 리스트 등)은 주석 처리되어 있음
			}
			// 액션이 처리된 후, 설정된 nextPage로 이동
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response); // 지정된 페이지로 포워딩
		}
	}
}
