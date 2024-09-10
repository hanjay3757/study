package com.peisia.c.admin;

import src.com.peisia.c.mysqlboard.display.DispBoard;
import src.com.peisia.c.util.Ci;
import src.com.peisia.c.util.Cw;

public class ProcAdmin {
	static private String cmd = "";

	static public void run() {
		Cw.wn("==== 관리자 메뉴 ====");

		adminmain: while (true) {
			cmd = Ci.r("관리자 암호를 입력하세요:");
			if (cmd.equals("1111")) {
				// 관리자 로그인 성공
				System.out.println("관리자님 어서오세요");

				// 관리자 모드 진행
//				adminRun();
				loop: while (true) {
					cmd = Ci.r("[1]게시판 제목 변경 / [x] 나가기");
					switch (cmd) {
					case "1":
						// todo 사이트 제목 변경 처리
						adminEditBoardTitle();
						break;
					case "x":
						break adminmain;
					}
				}

			} else {
				break; // 빠져나감
			}
		}
	}

	// 보이는거 페이지 1 + 정보제공 페이지 1 + 수정가능(관리자 페이지1)
	static void adminRun() {

	}

	static void adminEditBoardTitle() {
		Cw.wn("기존 게시판 제목:" + DispBoard.TITLE);
		cmd = Ci.rl("변경하실 제목을 입력하세요:");
		DispBoard.TITLE = cmd;
	}
}
