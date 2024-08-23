package com.practice;

import com.board.data.Data;
import com.board.data.Post;
import com.util.Ci;
import com.util.Cw;

public class ProcMenuUpdate {
	static void run() {
		Cw.wn("수정하쟈");
		String cmd = Ci.r("수정할 글 번호");
		for (Post p : Data.posts) {
			if (cmd.equals(p.instanceNo + "")) {
				// " " 하면 빈문자열 1빈칸 으로 인식 해서 안됨
				String content = Ci.rl("고칠내용");
				p.content = content;
				// 수정 완료
				Cw.wn("글 수정완료");
			}

		}
	}

}
