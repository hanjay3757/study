package com.practice.type;

import com.DispB.DispBoard;
import com.util.Ci;
import com.util.Cw;
import com.util.Db;

public class ProcBoard {
	void run() {
		DispBoard.title();
		Db.dbInit();
		Cw.wn("전체 글 수: " + Db.getPostCount());
		loop: while (true) {
			Db.dbPostCount();
			DispBoard.menuMain();
			String cmd = Ci.r("명령입력: ");
			switch (cmd) {
			case "1":
				ProcList.run();
				break;
			case "2":
				ProcRead.run();
				break;
			case "3":
				ProcWrite.run();
				break;
			case "4":
				ProcDel.run();
				break;
			case "5":
				ProcEdit.run();
				break;
			case "0":
				break;
			case "e":
				Cw.wn("프로그램 종료");
				break loop;
			}

		}
	}
}
