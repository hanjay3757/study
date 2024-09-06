package com.practice.type;

import com.util.Ci;
import com.util.Db;

public class ProcDel {
	static public void run() {
		String delNo = Ci.r("Delete NO");
		Db.dbExecuteUpdate("delete from board where b_no=" + delNo);
	}
}
