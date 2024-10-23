package com.tierconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	public static String tableNameBoard = "a등급"; // 게시물 테이블 이름
	public static String tableNameItems = "items"; // 아이템 테이블 이름

	// 데이터베이스 연결 및 Statement 객체 초기화
	static public void dbInit() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			st = con.createStatement();
			// 테이블이 없다면 생성
			createTableIfNotExists();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 'items' 테이블이 없으면 생성
	static public void createTableIfNotExists() {
		String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableNameItems + " ("
				+ "id INTEGER PRIMARY KEY AUTO_INCREMENT, " + "tier TEXT NOT NULL, " + "name TEXT NOT NULL)";
		try {
			st.execute(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SQL UPDATE 쿼리 실행
	static public void dbExcuteUpdate(String query) {
		Cw.wn("전송할 SQL : " + query);
		try {
			int resultCount = st.executeUpdate(query);
			Cw.wn("처리된 행 수 : " + resultCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시물 수를 조회하고 출력
	static public void dbPostCount() {
		try {
			Db.result = Db.st.executeQuery("SELECT COUNT(*) FROM " + tableNameBoard);
			Db.result.next();
			String count = Db.result.getString(1);
			Cw.wn("글 수: " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시물 수를 조회하여 반환
	static public int getPostCount() {
		int intCount = 0;
		try {
			Db.result = Db.st.executeQuery("SELECT COUNT(*) FROM " + tableNameBoard);
			if (Db.result.next()) {
				intCount = Db.result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intCount;
	}

	// 검색어로 게시물 수를 조회하여 반환
	static public int getPostCountSearch(String searchWord) {
		int intCount = 0;
		try {
			String query = "SELECT COUNT(*) FROM " + tableNameBoard + " WHERE b_title LIKE '%" + searchWord + "%'";
			Db.result = Db.st.executeQuery(query);
			if (Db.result.next()) {
				intCount = Db.result.getInt(1);
				Cw.wn("글 수: " + intCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return intCount;
	}

	// 로그인 검증
	static public boolean isProcLogin(String id, String pw) {
		boolean isValid = false;
		try {
			String query = "SELECT COUNT(*) FROM member WHERE s_id='" + id + "' AND s_pw='" + pw + "'";
			Db.result = Db.st.executeQuery(query);
			if (Db.result.next()) {
				int count = Db.result.getInt(1);
				Cw.wn("찾은 회원 수: " + count);
				isValid = (count == 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Cw.wn(isValid ? "로그인 성공" : "로그인 실패");
		return isValid;
	}

	// 'items' 테이블에 데이터 삽입
	static public void insertIntoItems(String tier, String name) {
		String query = "INSERT INTO " + tableNameItems + " (tier, name) VALUES (?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, tier);
			pstmt.setString(2, name);
			int rowsAffected = pstmt.executeUpdate();
			Cw.wn("삽입된 행 수: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 'items' 테이블 및 'member' 테이블 삭제
	static public void drops() {
		try {
			st.execute("DROP TABLE IF EXISTS " + tableNameItems);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			st.execute("DROP TABLE IF EXISTS member");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 공백 출력
	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" ");
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

	public static Connection getConnection() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}
}
