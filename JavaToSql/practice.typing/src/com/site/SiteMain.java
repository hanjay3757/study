package com.site;

import com.DispB.DispBoard;
import com.p_member.ProcMemberLogin;
import com.p_member.ProcMemberRegister;
import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class SiteMain {
	static private String cmd = "";
	static public String LoginedId = null;

	static public void run() {
		Db.dbInit();
		loop: while (true) {
			DispBoard.title();

			if (LoginedId == null) {
				cmd = Ci.r("[r]회원가입 [l]로그인 [e]프로그램종료");
			} else {
				Cw.wn(LoginedId + " 고객님 환영합니다.");
				cmd = Ci.r("[l]로그아웃 [b]자유게시판 [t]아이템 정보 [a]관리자 [e]프로그램종료 ");
			}
			switch (cmd) {

			case "r":
				if (LoginedId == null) {
					ProcMemberRegister.run();
				} else {
					Cw.wn("no!!!");
				}
				break;
			case "l":
				if (LoginedId == null) {
					LoginedId = ProcMemberLogin.run();
				} else {
					Cw.wn("로그아웃");
					LoginedId = null;
				}
				break;
			case "a":
				break;
			case "e":
				Cw.wn("프로그램 종료");
				break loop;
			case "b":
				if (LoginedId == null) {
					Cw.wn("장난 X");
				} else {
					Db.tableNameBoard = "아이템";
				}
				break;
			default:
				Cw.wn("testing");
			}
		}
	}
}
