package com.peisia.db;

public class Posts {
	private String title;
	private String content;
	private String videoUrl;
	private String board;
	private long timestamp;

	public Posts(String title, String content, String videoUrl, String board) {
		this.title = title;
		this.content = content;
		this.videoUrl = videoUrl;
		this.board = board;
		this.timestamp = System.currentTimeMillis();
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
