package com.DispB;

import com.util.Cw;

public class DispBoard {
	static private String TITLE = "영화 게시판";
	static private String VERSION = "v0.0.1";
	static private String FEAT = "sm.JWH";

	public static void title() {
		Cw.line();
		Cw.dot();
		Cw.space(20);
		Cw.w(TITLE);
		Cw.w(VERSION);
		Cw.w(FEAT);
		Cw.space(20);
		Cw.dot();
		Cw.wn();
		Cw.line();
	}

	public static void menuMain() {
		Cw.dot();
		Cw.w("[1]영화 리스트 [2]내용 읽기 [3]영화 추가 [4]삭제 [5]수정 [6]검색 [x]게시판 종료");
		Cw.dot();
		Cw.wn();
	}

	static public void titleList() {
		Cw.wn("==========================================");
		Cw.wn("================= 영화 목차 ==================");
		Cw.wn("==========================================");
		Cw.wn("번호 제목 작성자id 작성시간");
	}

	public static void replyBar() {
		Cw.wn("================= 댓글 리스트 ==================");
	}
}
