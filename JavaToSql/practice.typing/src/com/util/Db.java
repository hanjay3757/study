package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	// 데이터베이스 관련 기본 정보 (데이터베이스 이름, 사용자 ID, 비밀번호)
	static private String DB_NAME = "my_cat"; // 데이터베이스 이름
	static private String DB_ID = "root"; // 데이터베이스 접속 사용자 ID
	static private String DB_PW = "root"; // 데이터베이스 접속 사용자 비밀번호
	static public String tableNameBoard = "board"; // 게시판 테이블 이름 (일반적으로 사용되는 테이블 이름)

	// Connection 객체를 반환하는 메서드: 데이터베이스에 연결할 때 이 메서드를 사용합니다.
	// 메서드가 호출될 때마다 새로운 연결을 생성합니다.
	static public Connection getConnection() throws SQLException {
		try {
			// 1. MySQL JDBC 드라이버를 로드합니다.
			// Class.forName()은 JDBC 드라이버 클래스를 로드하는 역할을 합니다.
			// MySQL의 경우 'com.mysql.cj.jdbc.Driver'를 사용합니다.
			// 최신 JDBC에서는 자동으로 드라이버가 로드되므로, 이 부분은 생략해도 됩니다.
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// JDBC 드라이버를 로드하지 못했을 경우 발생하는 예외 처리
			e.printStackTrace();
			// SQLException을 던져서 호출한 곳에서 예외를 처리하도록 합니다.
			throw new SQLException("JDBC 드라이버 로드 실패");
		}

		// 2. DriverManager를 통해 데이터베이스 연결을 생성합니다.
		// "jdbc:mysql://localhost:3306/" + DB_NAME: 데이터베이스 URL
		// DB_ID, DB_PW: 데이터베이스에 연결할 때 사용할 사용자 ID와 비밀번호
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
	}

	// 데이터베이스에서 INSERT, UPDATE, DELETE 같은 데이터를 변경하는 쿼리를 실행하는 메서드입니다.
	// 여기서는 Statement를 사용하지만, SQL 인젝션 방지를 위해 PreparedStatement로 개선할 수 있습니다.
	static public void dbExecuteUpdate(String query) {
		Cw.wn("전송할sql:" + query); // 전송할 SQL 쿼리를 출력 (디버깅 목적)
		try (
				// 1. getConnection() 메서드를 호출하여 데이터베이스 연결을 얻습니다.
				Connection conn = getConnection();

				// 2. Statement 객체를 생성합니다. Statement는 SQL 쿼리를 실행하는 역할을 합니다.
				Statement st = conn.createStatement()) {
			// 3. executeUpdate()는 INSERT, UPDATE, DELETE와 같은 쿼리 실행 시 사용됩니다.
			int resultCount = st.executeUpdate(query);

			// 4. 처리된 행 수를 출력합니다. 이 값은 쿼리에 의해 영향을 받은 레코드 수를 나타냅니다.
			Cw.wn("처리된 행 수:" + resultCount);
		} catch (SQLException e) {
			// SQLException이 발생할 경우 예외 정보를 출력합니다.
			e.printStackTrace();
		}
	}

	// 게시글의 총 개수를 조회하는 메서드 (응답 없이 로그에만 출력하는 버전)
	static public void dbPostCount() {
		try (
				// 1. 데이터베이스 연결을 얻습니다.
				Connection conn = getConnection();

				// 2. Statement 객체를 생성합니다.
				Statement st = conn.createStatement()) {
			// 3. SQL SELECT 쿼리를 실행하여 게시글 수를 계산합니다.
			// b_reply_ori가 null인 경우(원글만)의 게시글 수를 세는 쿼리
			ResultSet result = st.executeQuery("select count(*) from " + tableNameBoard + " where b_reply_ori is null");

			// 4. ResultSet의 첫 번째 결과를 가져옵니다.
			if (result.next()) {
				// count(*) 값을 가져와서 게시글 수를 출력합니다.
				String count = result.getString("count(*)");
				Cw.wn("글 수:" + count);
			}
		} catch (SQLException e) {
			// SQLException이 발생할 경우 예외 정보를 출력합니다.
			e.printStackTrace();
		}
	}

	// 로그인 처리 메서드: 회원의 ID와 비밀번호를 기반으로 로그인 가능 여부를 확인합니다.
	static public boolean isProcLogin(String id, String pw) {
		String count = ""; // 결과를 저장할 변수
		try (
				// 1. 데이터베이스 연결을 얻습니다.
				Connection conn = getConnection();

				// 2. Statement 객체를 생성합니다.
				Statement st = conn.createStatement()) {
			// 3. 회원 정보를 확인하는 SQL 쿼리 실행
			// 주어진 ID와 비밀번호가 일치하는 회원의 수를 세는 쿼리
			ResultSet result = st
					.executeQuery("select count(*) from member where s_id='" + id + "' and s_pw='" + pw + "'");

			// 4. 결과에서 회원 수를 가져옵니다. (결과는 0 또는 1이어야 함)
			if (result.next()) {
				count = result.getString("count(*)");
				Cw.wn("찾은 회원 수:" + count);
			}
		} catch (SQLException e) {
			// SQLException이 발생할 경우 예외 정보를 출력합니다.
			e.printStackTrace();
		}

		// 5. 회원 수가 1이면 로그인 성공, 그렇지 않으면 실패로 처리합니다.
		if (count.equals("1")) {
			Cw.wn("[로그인 성공]"); // 회원이 1명일 때 로그인 성공 메시지 출력
			return true; // 로그인 성공
		} else {
			Cw.wn("[로그인 실패]"); // 회원이 없거나 비밀번호가 틀린 경우 로그인 실패 메시지 출력
			return false; // 로그인 실패
		}
	}
}
