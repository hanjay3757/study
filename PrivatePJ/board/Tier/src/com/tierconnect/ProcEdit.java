package com.tierconnect;

public class ProcEdit {
	static public void run() {
		String editNo = Ci.r("수정할 글번호를 입력해주세요:");
		String edTitle = Ci.r("제목을 입력해주세요:");
		String edId = Ci.r("작성자id를 입력해주세요:");
		String edContent = Ci.r("글내용을 입력해주세요:");
		Db.dbExcuteUpdate("update " + Db.tableNameBoard + " set b_title='" + edTitle + "',b_id='" + edId
				+ "',b_datetime=now(),b_text='" + edContent + "' where b_no=" + editNo);
	}
}
