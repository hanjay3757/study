package com.practice.type;

import com.util.Ci;
import com.util.Db;

public class ProcEdit {
	static public void run() {
		String editNo = Ci.r("수정할 번호");
		String edTitle = Ci.read("title");
		String edId = Ci.read("id?");
		String edContent = Ci.read("content");
		Db.dbExecuteUpdate("update board set b_title='" + edTitle + "',b_id'" + edId + "'b_datetime=now(),b_text='"
				+ edContent + "'where b_no=" + editNo);
	}
}
