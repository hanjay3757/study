package com.tierconnect;

public class ProcMemberRegister {
	static private String cmd = "";

	static public void run() {
		Cw.wn("======== 회원가입 =========");
		String id = "";
		String pw = "";
		while (true) {
			id = Ci.r("아이디");
			if (id.length() > 0) {
				break;
			} else {
				Cw.wn("장난x");
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
		// id,pw 를 디비에 저장. ex. insert into member values('nyang','1234');
		Db.dbExcuteUpdate("insert into member values('" + id + "','" + pw + "')");
		Cw.wn("[가입완]");
	}
}