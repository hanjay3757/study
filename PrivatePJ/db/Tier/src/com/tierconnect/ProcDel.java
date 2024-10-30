package com.tierconnect;

public class ProcDel {
	static public void run() {
		String delNo = Ci.r("삭제할 글번호를 입력해주세요:");
		// - 기능 수정 - 게시판 - 글 삭제 시 로그인 한 본인만 삭제 가능하도록 수정. JavaMysqlSitePeisia_v0.0.6
		Db.dbExcuteUpdate(
				"delete from " + Db.tableNameBoard + " where b_no=" + delNo + " and b_id='" + BoardT.LoginedId + "'");
	}
}
