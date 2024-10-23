package com.peisia.c.board;

import com.peisia.c.board.data.Data;
import com.peisia.c.board.data.Post;
import com.peisia.util.Ci;
import com.peisia.util.Cw;

public class ProcMenuWrite {
	static void run() {
		//todo
		//임시
		Cw.wn("쓰기임");
		String title;
		while(true) {
//			title=Ci.r("글제목");
			title=Ci.rl("글제목");
			if(title.length()>0) {
				break;
			}else {
				Cw.wn("장난x");
			}
		}
		String content;
		while(true) {
//			content=Ci.r("글내용");
			content=Ci.rl("글내용");
			if(content.length()>0) {
				break;
			}else {
				Cw.wn("장난x");
			}
		}
		String writer;
		while(true) {
			writer=Ci.r("작성자");
			if(writer.length()>0) {
				break;
			}else {
				Cw.wn("장난x");
			}
		}
		
//		Post p = new Post(1, "고양이", "귀엽다", "개", 0);
		Post p = new Post(title, content, writer, 0);
		Data.posts.add(p);
		Cw.wn("글 작성됨");
	}

}
