package com.practice.type;

import java.sql.SQLException;

import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcWrite {
	static public void run() {
		String title = Ci.read("제목을 입력해주세요: ");
		String content = Ci.read("글 내용을 입력해주세요");
		String id = Ci.read("id");
		try {
			Db.st.executeUpdate("insert into board (b_title,b_id,b_datetime,b_text,b_hit)" + "values ('" + title + "','"
					+ id + "',now(),'" + content + "',0)");
			Cw.wn("Complete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
