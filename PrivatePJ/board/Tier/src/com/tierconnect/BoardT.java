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
				cmd = Ci.r("[l]로그아웃 [b]자유게시판 [t]장터게시판 [a]관리자 [e]프로그램종료 ");
			}
			switch (cmd) {
			case "r":
				if (LoginedId == null) {
					ProcMemberRegister.run();
				}

			}
		}
	}
}
