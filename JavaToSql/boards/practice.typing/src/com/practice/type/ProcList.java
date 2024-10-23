package com.practice.type;

import java.sql.SQLException;

import com.display.Disp;
import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcList {
	static public final int PER_PAGE = 3;

	static public void run() {
		int startIndex = 0; // 현재 페이지 첫글 인덱스
		int currentPage = 1; // 현재 페이지

		int totalPage = 0;
		if (Db.getPostCount() % PER_PAGE > 0) {
			totalPage = Db.getPostCount() / PER_PAGE + 1;
		} else {
			totalPage = Db.getPostCount() / PER_PAGE;
		}
		Cw.wn("총 페이지 수 : " + totalPage);

		String cmd;
		while (true) {
			cmd = Ci.r("페이지 번호 입력[이전 메뉴로:x]");
			if (cmd.equals("x")) {
				break;
			}
			currentPage = Integer.parseInt(cmd);
			if (currentPage > totalPage || currentPage < 1) {
				Cw.wn("페이지 범위에 맞는 값을 입력하시오");
				continue;
			}

			startIndex = (currentPage - 1) * PER_PAGE; // 페이지의 첫 인덱스를 계산해서 저장
			Disp.titleList();
			String sql = "select * from board where b_reply_ori is null limit" + startIndex + "," + PER_PAGE;
			try {
				Cw.wn("전송한 sql문 : " + sql);
				Db.result = Db.st.executeQuery(sql);
				while (Db.result.next()) {
					String no = Db.result.getString("b_no");
					String title = Db.result.getString("b_title");
					String id = Db.result.getString("b_id");
					String datetime = Db.result.getString("b_datetime");
					Cw.wn(no + " " + title + " " + id + " " + datetime);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
