package com.tierconnect;

public class ProcWrite {
	static public void run() {
		String title = Ci.r("제목을 입력해주세요:");
		String content = Ci.r("글내용을 입력해주세요:");
		try { // JavaMysqlSitePeisia_v0.0.4
			Db.st.executeUpdate("insert into " + Db.tableNameBoard + " (b_title,b_id,b_datetime,b_text,b_hit)"
					+ " values ('" + title + "','" + BoardT.LoginedId + "',now(),'" + content + "',0)");
			Cw.wn("글등록 완료");
		} catch (Exception e) {
		}
	}
}