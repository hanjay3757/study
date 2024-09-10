package com.tierconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	// 데이터베이스 연결 객체
	static public Connection con = null;
	// SQL 명령을 실행하기 위한 Statement 객체
	static public Statement st = null;
	// SQL 쿼리의 결과를 저장할 ResultSet 객체
	static public ResultSet result = null;

	// 데이터베이스 이름, 사용자 ID 및 비밀번호
	static private String DB_NAME = "my_cat";
	static private String DB_ID = "root";
	static private String DB_PW = "root";

	// 데이터베이스 테이블 이름
	public static String tableNameBoard = "board";

	// 데이터베이스 연결 및 Statement 객체 초기화
	static public void dbInit() {
		try {
			// 데이터베이스 연결 생성
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			// Statement 객체 생성
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}

	// SQL UPDATE 쿼리 실행
	static public void dbExcuteUpdate(String query) {
		Cw.wn("전송할 sql : " + query);
		try {
			// 쿼리 실행 및 처리된 행 수 출력
			int resultCount = st.executeUpdate(query);
			Cw.wn("처리된 행 수 : " + resultCount);
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}

	// 게시물 수를 조회하고 출력
	static public void dbPostCount() {
		try {
			// 게시물 수 쿼리 실행
			Db.result = Db.st.executeQuery("select count(*) from " + tableNameBoard + " where b_reply_ori is null");
			Db.result.next();
			String count = Db.result.getString("count(*)");
			Cw.wn("글 수:" + count);
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}

	// 게시물 수를 조회하여 반환
	static public int getPostCount() {
		String count = "";
		try {
			// 게시물 수 쿼리 실행
			Db.result = Db.st.executeQuery("select count(*) from " + tableNameBoard + " where b_reply_ori is null");
			Db.result.next();
			count = Db.result.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
		// 문자열을 정수로 변환하여 반환
		int intCount = Integer.parseInt(count);
		return intCount;
	}

	// 검색어로 게시물 수를 조회하여 반환
	static public int getPostCountSearch(String searchWord) {
		String count = "";
		try {
			// 검색어 포함 게시물 수 쿼리 실행
			Db.result = Db.st.executeQuery("select count(*) from " + tableNameBoard + " where b_reply_ori is null"
					+ " and b_title like '%" + searchWord + "%'");
			Db.result.next();
			count = Db.result.getString("count(*)");
			Cw.wn("글 수:" + count);
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
		// 문자열을 정수로 변환하여 반환
		int intCount = Integer.parseInt(count);
		return intCount;
	}

	// 로그인 검증
	static public boolean isProcLogin(String id, String pw) {
		String count = "";
		try {
			// 사용자 ID와 비밀번호로 로그인 검증 쿼리 실행
			Db.result = Db.st.executeQuery("select count(*) from member where s_id='" + id + "' and s_pw='" + pw + "'");
			Db.result.next();
			count = Db.result.getString("count(*)");
			Cw.wn("찾은회원 수 : " + count);
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
		// 로그인 성공 여부 반환
		if (count.equals("1")) {
			Cw.wn("로그인 성공");
			return true;
		} else {
			Cw.wn("로그인 실패");
			return false;
		}
	}

	// 콘솔에 문자열 출력
	public static void wn(String s) {
		System.out.print(s);
	}

	// 콘솔에 줄 바꿈 출력
	public static void wn() {
		System.out.println();
	}

	// 콘솔에 문자열 출력
	public static void w(String s) {
		System.out.print(s);
	}

	// 콘솔에 줄 바꿈 출력
	public static void w() {
		System.out.println();
	}

	// 'items' 테이블 및 'member' 테이블 삭제
	static public void drops() {
		try {
			st.execute("DROP TABLE IF EXISTS items");
		} catch (SQLException e1) {
			e1.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}

		try {
			st.execute("DROP TABLE IF EXISTS member");
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}

	// 'items' 테이블 생성
	static public void create() {
		String createTableSQL = "CREATE TABLE Items (" + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "tier TEXT NOT NULL, " + "name TEXT NOT NULL)";
		try {
			st.execute(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}

	// 공백 출력
	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" ");
		}
	}
}
