package com.site;

import com.display.Disp;
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
			Disp.entranceTitle();

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
				}
			}
		}
	}
}
