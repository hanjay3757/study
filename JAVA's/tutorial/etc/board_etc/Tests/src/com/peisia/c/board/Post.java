package com.peisia.c.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

	private int no = 0;
	private String title;
	private String content;
	private String writer;
	String writeTime;

	public Post(int no, String title, String content, String writer) {
		this.no = no; // 여전히 이 코드가 작동함. 이유는? 자동 형변환이 일어남(박싱)
		this.title = title;
		this.content = content;
		this.writer = writer;

		/* [버전0.0.8] */
		// 현재시간을 가져와서
		// 일정 포맷으로 지정하고
		// writeTime 변수에 대입해주기
		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
//		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm");		//시간을 12시간제로 표시 hh
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");	//시간을 24시간제로 표시 HH
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm"); // 시간을 24시간제로 표시 HH
		writeTime = sdf.format(now);
	}

	// 기존 info 함수를 지우고
	// Object 클래스의 toString 함수를 재정의하여 리스트 출력에 쓰이도록 함
//	void info() {
//		String s  = String.format("글번호: %s 제목: %s 작성자: %s", no, title, writer);
//		System.out.println(s);
//	}
	@Override
	public String toString() {
		return String.format("글번호: %s 제목: %s 작성자: %s", no, title, writer);
	}

	void infoForRead() {
		String s = String.format("글번호: %s 제목: %s 작성자: %s 작성시간: %s", no, title, writer, writeTime);
		System.out.println("================================================");
		System.out.println(s);
		System.out.println("================================================");
		System.out.println(content);
		System.out.println("================================================");
	}

	// 참고: getㅇㅇㅇ setㅁㅁㅁ 함수 자동 생성하는법
	// 현재 편집창에서 마우스우클릭-소스-제너레이트 게터세터
	// -자동으로 생성하고 싶은 멤버변수들 체크하고 엔터. 끝.
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
}