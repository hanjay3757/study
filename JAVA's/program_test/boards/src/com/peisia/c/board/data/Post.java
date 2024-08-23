package com.peisia.c.board.data;

import java.time.LocalDate;

import com.peisia.util.Cw;

public class Post {
	static public int no = 0;
	public String title;
	public String content;
	public String writer;
	public int hit;
	public String date;
	public Post(String title, String content, String writer, int hit) {
		// 글 번호를 1 증가 시켜 주기. static 변수라 계속 남아있고, 이 Post 객체를 아무리 많이 만들어도
		// 딱 박스 한개만 생기고 관리됨. 그래서 이런 글 번호 같은거 담기 좋음. 헷갈리면 딴데가 빼도 ㄱㅊ.
		no = no + 1;	
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hit = hit;
		LocalDate now = LocalDate.now();
		date = now.toString();
	}
	public void info() {
		Cw.w("글제목:"+title);
		Cw.w(" 작성자:"+writer);
		Cw.w(" 조회수:"+hit);
		Cw.wn(" 작성일:"+date);
		Cw.wn("글내용:"+content);
	}
	
}
