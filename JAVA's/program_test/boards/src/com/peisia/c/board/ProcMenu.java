package com.peisia.c.board;

import com.peisia.c.board.display.Disp;
import com.peisia.util.Ci;
import com.peisia.util.Cw;

public class ProcMenu {
	static void run() {
		Disp.menuMain();
		loop:
		while(true) {
			String cmd = Ci.r("명령");
			switch(cmd) {
			case "1":
				ProcMenuList.run();
				//todo
				//리스트
				break;
			case "2":
				//todo
				//읽기
				ProcMenuRead.run();
				break;
			case "3":
				//todo
				//쓰기
				ProcMenuWrite.run();
				break;
			case "4":
				//todo
				//삭제
				ProcMenuDel.run();
				break;
			case "e":
				System.out.println("프로그램 종료");
				break loop;
			default:
				Cw.wn("장난x");
				break;
			}
		}
	}

}
