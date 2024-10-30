package com.practice.type;

import java.sql.SQLException;

import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcRead {
	static public void run() {
		String readNo = Ci.r("읽을");
		try {
			Db.result = Db.st.executeQuery("select * from board where b_no=" + readNo);
			Db.result.next();
			String title = Db.result.getString("b_title");
			String content = Db.result.getString("b_content");
			Cw.wn("글 제목" + title);
			Cw.wn("글 내용" + content);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
