package com.p_member;

import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcMemberRegister {
	static private String cmd = "";

	static public void run() {
		Cw.wn("Register");
		String id = "";
		String pw = "";
		while (true) {
			id = Ci.r("ID");
			if (id.length() > 0) {
				break;
			} else {
				Cw.wn("ERROR");
			}
		}
		while (true) {
			pw = Ci.r("PassWord");
			if (pw.length() > 0) {
				break;
			} else {
				Cw.wn("장난???");
			}
		}
		Db.dbExecuteUpdate("insert into member values('" + id + "','" + pw + "')");
		Cw.wn("가입 : ('" + id + "','" + pw + "') 완료");
	}

}
