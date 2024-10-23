package com.board.data;

import java.time.LocalDate;

import com.util.Cw;

public class Post {
	static public int no = 0;
	public int instanceNo = 0;
	public String title;
	public String content;
	public String writer;
	public int hit;
	public String date;

	public Post(String title, String content, String writer, int hit) {
		no = no + 1;
		instanceNo = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.hit = hit;
		LocalDate now = LocalDate.now();
		// 변수 now 현재시간 데이터 갖고와서 적용.
		date = now.toString();
		// toString으로 데이터 문자로 리턴
	}

	public void infoForList() {
//		Cw.w(instanceNo);
//		// 문자열 하고 + 숫자열하면 문자열 변경됨 //숫자 옆에 + "" 하면 문자열취급함
//		Cw.w(title);
//		Cw.w(writer);
//		Cw.w(hit);
//		Cw.wn(date);

		Cw.w("글번호:" + instanceNo);
		Cw.w(" 글제목:" + title);
		Cw.w(" 작성자:" + writer);
		Cw.w(" 조회수:" + hit);
		Cw.wn(" 작성일:" + date);

	}

	public void infoForRead() {
		Cw.w("글제목:" + title);
		Cw.w(" 작성자:" + writer);
		Cw.w(" 조회수:" + hit);
		Cw.wn(" 작성일:" + date);
		Cw.wn("글내용:" + content);
	}
}
