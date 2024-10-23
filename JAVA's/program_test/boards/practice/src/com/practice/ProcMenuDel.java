package com.practice;

import com.board.data.Data;
import com.util.Ci;
import com.util.Cw;

public class ProcMenuDel {
	static void run() {
		Cw.wn("삭제일까");
		String cmd = Ci.r("삭제할 글번호");
		int tempSearchIndex = 0;
		for (int i = 0; i < Data.posts.size(); i++) {
			if (cmd.equals(Data.posts.get(i).instanceNo + "")) {
				tempSearchIndex = i;
			}
		}
		Data.posts.remove(tempSearchIndex);
		Cw.wn("글 수 : " + Data.posts.size());
	}
}
