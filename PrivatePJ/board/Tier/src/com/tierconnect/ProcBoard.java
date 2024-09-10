package com.tierconnect;

public class ProcBoard {

	static public void run() {

		Db.getPostCount();
		loop: while (true) {

			String cmd = Ci.r("[1]리스트 [2]읽기 [3]쓰기 [4]삭제 [5] 수정  [6]리스트검색 [x]종료");
			switch (cmd) {
			case "1": // 글리스트
				ProcList.run();
				break;
			case "2": // 글읽기
				ProcRead.run();
				break;
			case "3": // 글쓰기
				ProcWrite.run();
				break;
			case "4": // 글삭제
				ProcDel.run();
				break;
			case "5": // 글수정
				ProcEdit.run();
				break;
			case "6": // 글리스트-검색
				ProcList.search();
				break;
			case "x": // 게시판 종료
				Cw.wn("사이트 메인으로 이동");
				break loop;
			}
		}
	}
}