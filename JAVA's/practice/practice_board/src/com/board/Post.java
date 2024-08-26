package com.board;

import java.text.DecimalFormat;

public class Post {
	int no = 0;

	String title;
	String content;
	String writer;
	DecimalFormat formatter = new DecimalFormat("#,###");

	public Post(int no, String title, String content, String writer) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	void info() {
		String s = String.format("[제목: %s 내용: %s 작가: %s", formatter.format(no), title, writer);
		System.out.println(s);
	}

	void infoForRead() {
		String s = String.format("[제목: %s 내용: %s 작가: %s", formatter.format(no), title, writer);
		System.out.println(s);
	}
}