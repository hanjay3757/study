package com.practice;

import com.board.data.Data;
import com.c.board.display.Disp;

public class Board {
	public static final String VERSION = "v0.0.5";
	public static final String TITLE = "고양이 게시판(" + VERSION + ") feat. sm.jwh";

	public void run() {
		Data.loadData();
		Disp.title();
		ProcMenu.run();
	}
}
