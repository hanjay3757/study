package com.tierconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pack {
	static public void run() {
		String title = Ci.rl("제목을 입력해주세요:");
		String content = Ci.rl("글내용을 입력해주세요:");

		// Connection 객체 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 데이터베이스 연결 얻기
			conn = Db.getConnection(); // Db.getConnection()은 적절한 Connection 객체를 반환하는 메서드여야 합니다.

			// SQL 쿼리 작성
			String query = "INSERT INTO " + Db.tableNameBoard
					+ " (b_title, b_id, b_datetime, b_text, b_hit) VALUES (?, ?, NOW(), ?, ?)";

			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(query);

			// 파라미터 설정
			pstmt.setString(1, title); // 제목
			pstmt.setString(2, SiteMain.loginedId); // 로그인된 사용자 ID
			pstmt.setString(3, content); // 내용
			pstmt.setInt(4, 0); // 조회수 (기본값 0)

			// 쿼리 실행
			pstmt.executeUpdate();
			Cw.wn("글등록 완료");

		} catch (SQLException e) {
			e.printStackTrace();
			Cw.wn("글등록 실패: " + e.getMessage());
		} finally {
			// 자원 해제
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
