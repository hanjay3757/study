package com.tierconnect;

public class BoardT {
	static private String cmd = "";
	static public String LoginedId = null;

	public String run() {
		Db.dbInit();
		System.out.println("DB connect");
		while (true) {
			if (LoginedId == null) {
				cmd = Ci.r("[r]회원가입 [l]로그인 [e]프로그램종료");
			} else {
				Cw.wn(LoginedId);
				cmd = Ci.r("[l]로그아웃 [a]a등급 [b]b등급 [3]게시판 관리 [t]관리자 [e]프로그램종료 ");
			}
			switch (cmd) {
			case "r":
				if (LoginedId == null) {
					ProcMemberRegister.run();
				}
			case "3":
				if (LoginedId == null) {
					LoginedId = ProcMemberLogin.run();
				} else {
					Cw.wn("관리요");
					LoginedId = null;
					ProcBoard.run();
				}
			case "l":
				if (LoginedId == null) {
					LoginedId = ProcMemberLogin.run();
				} else {
					Cw.wn("logOUT");
					LoginedId = null;
				}
				break;
			case "a":
				if (LoginedId == null) {
					Cw.wn("로그인이나 하슈");
				} else {
					Db.tableNameBoard = "A등급";
					ProcBoard.run();
				}
			case "b":
				if (LoginedId == null) {
					Cw.wn("로그인이나 하슈");
				} else {
					Db.tableNameBoard = "B등급";
					ProcBoard.run();
				}
			}
		}
	}
}
