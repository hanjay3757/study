package com.c.board.display;

import com.practice.Board;
import com.util.Cw;

public class Disp {
	public static void title() {
		Cw.line();
		Cw.dot();
		Cw.space(15);
		Cw.w(Board.TITLE);
		Cw.space(15);
		Cw.dot();
		Cw.wn();
		Cw.line();
	}

	public static void menuMain() {
		Cw.dot();
		Cw.w("[1.글 리스트/2.글읽기/3.글쓰기/4.글삭제/e.종료]");
		Cw.dot();
		Cw.wn();
	}
}
