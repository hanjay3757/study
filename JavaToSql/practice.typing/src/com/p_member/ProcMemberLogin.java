package com.p_member;

import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcMemberLogin {
	static public String run() {
		Cw.wn("로그인");
		String id = "";
		String pw = "";
		while (true) {
			id = Ci.r("아이디");
			if (id.length() > 0) {
				break;
			} else {
				Cw.wn("돌아가");
			}
		}
		while (true) {
			pw = Ci.r("암호");
			if (pw.length() > 0) {
				break;
			} else {
				Cw.wn("장난x");
			}
		}
		if (Db.isProcLogin(id, pw)) { // 로그인 처리
			return id;
		} else { // 로그인 실패 처리
			Cw.wn("로그인 실패");
			return null;
		}
	}
}