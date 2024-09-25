package com.peisia.db;

public class Dto2 {
	public String no;
	public String title;
	public String id;
	public String datetime;
	public String hit;
	public String text;
	public String replyCount;
	public String replyOri;

	public Dto2(String title, String id, String text) {
		this.title = title;
		this.id = id;
		this.text = text;
	}

	public Dto2(String no, String title, String id, String datetime, String hit, String text, String replyCount,
			String replyOri) {
		super();
		this.no = no;
		this.title = title;
		this.id = id;
		this.datetime = datetime;
		this.hit = hit;
		this.text = text;
		this.replyCount = replyCount;
		this.replyOri = replyOri;
	}

	public Dto2(String title, String text) {
		this.title = title;
		this.text = text;
	}
}
