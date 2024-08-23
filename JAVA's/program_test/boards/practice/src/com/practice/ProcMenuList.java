package com.practice;

import com.board.data.Data;
import com.board.data.Post;
import com.util.Cw;

public class ProcMenuList {
	static void run() {
		Cw.wn("list임");
		for (Post q : Data.posts) {
			q.infoForList();
		}
	}
}
