package com.tierconnect;

public class ProcMemberLogin {
	static public String run() {
		Cw.wn("==================로그인==================");
		String id = "";
		String pw = "";
		while (true) {
			id = Ci.r("아이디");
			if (id.length() > 0) {
				break;
			} else {
				Cw.wn("ㄴㄴ");
			}
		}
		while (true) {
			pw = Ci.r("비번");
			if (pw.length() > 0) {
				break;
			} else {
				Cw.wn("ㄴㄴ");
			}
		}
		if (Db.isProcLogin(id, pw)) {
			return id;
		} else {
			Cw.wn("로그인 실패");
			return null;
		}
	}
}