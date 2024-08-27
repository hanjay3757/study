package com.board.test;

import java.text.DecimalFormat;

public class Posts {
	int no = 0;
	String title;
	String content;
	String writer;

	DecimalFormat formatter = new DecimalFormat("#,###");

	public Posts(int no, String title, String content, String writer) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	void info() {
		String s = String.format("[No. %s List: %s Read: %s Write: %s]", formatter.format(no), title, content, writer);
		System.out.println(s);
	}

	void infoForRead() {
		String s = String.format("[No. %s List: %s Read: %s Write: %s]", formatter.format(no), title, content, writer);
		System.out.println(s);
	}
}
