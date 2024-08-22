package com.peisia.c.board;

import com.peisia.c.board.display.Disp;

public class Board {
	public static final String VERSION = "v0.0.1";
	public static final String TITLE = "고양이 게시판 (" + VERSION + ") feat. sm.ahn";

	public void run() {
		Disp.title();
		ProcMenu.run();
	}
}
