package com.practice;

import com.board.data.Data;
import com.board.data.Post;
import com.util.Ci;
import com.util.Cw;

public class ProcMenuWrite {
	static void run() {
		Cw.wn("쓰기임");
		String title;
		while (true) {
			title = Ci.rl("글제목");
			if (title.length() > 0) {
				break;
			} else {
				Cw.wn("장난치지 말라고");
			}
		}
		String content;
		while (true) {
			content = Ci.rl("글내용");
			if (content.length() > 0) {
				break;
			} else {
				Cw.wn("아직도 장난치냐?");
			}
		}
		String writer;
		while (true) {
			writer = Ci.rl("작성자");
			if (writer.length() > 0) {
				break;
			} else {
				Cw.wn("그렇게 살아라");
			}
		}
		Post p = new Post(title, content, writer, 0);
		Data.posts.add(p);
		Cw.wn("글 작성됨");
	}
}
