package com.board2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private int no = 0;
	private String title;
	private String content;
	private String writer;
	private String writeTime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public Post(int no, String title, String content, String writer, String writeTime) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeTime = writeTime;
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd a hh:mm");
		writeTime = sdf.format(now);
	}

	public String toString() {
		return String.format("글번호: %s 제목: %s 작성자: %s 작성시간: %s", no, title, writer, writeTime);
	}

	void infoForRead() {
		String s = String.format("글번호: %s 제목: %s 작성자: %s 작성시간: %s", no, title, writer, writeTime);
		System.out.println("================================================");
		System.out.println(s);
		System.out.println("================================================");
		System.out.println(content);
		System.out.println("================================================");
	}
}