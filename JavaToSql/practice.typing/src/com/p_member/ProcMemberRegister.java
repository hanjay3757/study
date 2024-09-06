package com.p_member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcMemberRegister {
	static private String cmd = "";

	static public void run() {
		Cw.wn("Register");
		String id = "";
		String pw = "";

		// ID 입력
		while (true) {
			id = Ci.r("ID");
			if (id.length() > 0) {
				break;
			} else {
				Cw.wn("ERROR: ID는 필수 입력 사항입니다.");
			}
		}

		// 비밀번호 입력
		while (true) {
			pw = Ci.r("PassWord");
			if (pw.length() > 0) {
				break;
			} else {
				Cw.wn("ERROR: 비밀번호는 필수 입력 사항입니다.");
			}
		}

		// 데이터베이스에 값 삽입
		String sql = "INSERT INTO member (s_id, s_pw) VALUES (?, ?)";
		try (Connection conn = Db.getConnection(); // Connection 얻어오기
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// PreparedStatement에 값 설정
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			// 쿼리 실행
			int result = pstmt.executeUpdate();
			if (result > 0) {
				Cw.wn("가입 완료: ('" + id + "','" + pw + "')");
			} else {
				Cw.wn("가입 실패");
			}

		} catch (SQLException e) {
			// SQL 예외 처리
			Cw.wn("데이터베이스 오류: " + e.getMessage());
		}
	}
}
