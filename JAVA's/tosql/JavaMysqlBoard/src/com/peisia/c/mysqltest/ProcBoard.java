package com.peisia.c.mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProcBoard {
	public static final int PER_PAGE = 2;

	Connection con = null;
	Statement st = null;
	ResultSet result = null;
	Scanner sc = new Scanner(System.in);

//#연결문 sql이랑 연결
	void run() {
		Display.showTitle();
		dbInit();

		int startIndex = 0; // 현재 페이지의 첫 글 인덱스
		int currentPage = 1; // 현재 페이지

		loop: while (true) {
			dbPostCount();
			Display.showMainMenu();
			System.out.println("명령입력: ");
			String cmd = sc.next();
			switch (cmd) {
			case "1": // 글리스트
				startIndex = (currentPage - 1) * PER_PAGE; // 페이지의 첫 인덱스를 계산해서 저장하기

				System.out.println("==========================================");
				System.out.println("================= 글리스트 ==================");
				System.out.println("==========================================");
				System.out.println("글번호 글제목 작성자id 작성시간");
				try {
					String sql = "select * from board limit " + startIndex + "," + PER_PAGE;
					System.out.println("전송한sql문:" + sql);
					result = st.executeQuery(sql);

					while (result.next()) {
						String no = result.getString("b_no");
						String title = result.getString("b_title");
						String id = result.getString("b_id");
						String datetime = result.getString("b_datetime");
						System.out.println(no + " " + title + " " + id + " " + datetime);
					}
				} catch (SQLException e) {
					System.out.println("SQL 오류: " + e.getMessage());
				}
				break;
			case "2": // 글읽기
				System.out.println("읽을 글 번호를 입력해주세요:");
				String readNo = sc.next();
				try {
					result = st.executeQuery("select * from board where b_no =" + readNo);
					if(result.next()) {
						String title = result.getString("b_title");
						String content = result.getString("b_text");
						System.out.println("글제목: " + title);
						System.out.println("글내용: " + content);
					} else {
						System.out.println("해당 글이 존재하지 않습니다.");
					}
				} catch (SQLException e) {
					System.out.println("SQL 오류: " + e.getMessage());
				}
				break;
			case "3": // 글쓰기
				sc.nextLine();
				System.out.println("제목을 입력해주세요:");
				String title = sc.nextLine();
				System.out.println("글내용을 입력해주세요:");
				String content = sc.nextLine();
				System.out.println("작성자id를 입력해주세요:");
				String id = sc.next();
				try {
					String sql = "insert into board (b_title,b_id,b_datetime,b_text,b_hit) values (?,?,now(),?,0)";
					java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, title);
					pstmt.setString(2, id);
					pstmt.setString(3, content);
					pstmt.executeUpdate();
					System.out.println("글등록 완료");
				} catch (SQLException e) {
					System.out.println("SQL 오류: " + e.getMessage());
				}
				break;
			case "4": // 글삭제
				System.out.println("삭제할 글번호를 입력해주세요:");
				String delNo = sc.next();
				dbExecuteUpdate("delete from board where b_no=" + delNo);
				break;
			case "5": // 글수정
				System.out.println("수정할 글번호를 입력해주세요:");
				String editNo = sc.next();
				System.out.println("제목을 입력해주세요:");
				sc.nextLine();
				String edTitle = sc.nextLine();
				System.out.println("작성자id를 입력해주세요:");
				String edId = sc.next();
				System.out.println("글내용을 입력해주세요:");
				sc.nextLine();
				String edContent = sc.nextLine();

				try {
					String sql = "update board set b_title=?, b_id=?, b_datetime=now(), b_text=? where b_no=?";
					java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, edTitle);
					pstmt.setString(2, edId);
					pstmt.setString(3, edContent);
					pstmt.setString(4, editNo);
					pstmt.executeUpdate();
					System.out.println("글수정 완료");
				} catch (SQLException e) {
					System.out.println("SQL 오류: " + e.getMessage());
				}
				break;
			case "0": // 관리자
				break;
			case "e": // 프로그램 종료
				System.out.println("프로그램종료");
				break loop;
			}
		}
	}

	private void dbInit() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat?serverTimezone=UTC", "root", "root");
			st = con.createStatement();
		} catch (SQLException e) {
			System.out.println("SQL 연결 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류: " + e.getMessage());
		}
	}

	private void dbExecuteUpdate(String query) {
		try {
			int resultCount = st.executeUpdate(query);
			System.out.println("처리된 행 수:" + resultCount);
		} catch (SQLException e) {
			System.out.println("SQL 오류: " + e.getMessage());
		}
	}

	private void dbPostCount() {
		try {
			result = st.executeQuery("select count(*) as count from board");
			result.next();
			String count = result.getString("count");
			System.out.println("글 수:" + count);
		} catch (SQLException e) {
			System.out.println("SQL 오류: " + e.getMessage());
		}
	}
}
