package com.tierconnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcWrite {
	static public void run() {
		String title = Ci.r("제목을 입력해주세요:");
		String content = Ci.r("글내용을 입력해주세요:");

		try {
			// 예시: tier와 name 값을 정의 (제목과 내용을 사용)
			String tier = title; // 실제 로직에 맞게 수정
			String name = content; // 실제 로직에 맞게 수정

			// INSERT 쿼리 작성
			String query = "INSERT INTO a등급 (tier, name) VALUES (?, ?)";

			// PreparedStatement 사용을 권장
			try (PreparedStatement pstmt = Db.con.prepareStatement(query)) {
				pstmt.setString(1, tier);
				pstmt.setString(2, name);
				int rowsAffected = pstmt.executeUpdate(); // 쿼리 실행
				Cw.wn("삽입된 행 수: " + rowsAffected); // 삽입된 행 수 출력
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
		}
	}
}
