package com.board;

public class Post {
	int no = 0;
	String title;
	String content;
	String writer;

	public Post(int no, String title, String content, String writer) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	void info() {
		String s = String.format(" 글 번호 :  %s 제목 : %s 작성자 : %s", no, title, writer);
		System.out.println(s);
	}

	void infoForRead() {
		String s = String.format("글번호 : %s 제목 : %s 작성자 : %s", no, title, writer);
		System.out.println("==========================================");
		System.out.println(s);
		System.out.println("==========================================");
		System.out.println(content);
		System.out.println("==========================================");
	}
}
