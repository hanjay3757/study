package com.kiosk.v8;

import com.kiosk.util.So;

public class Display {
	// 일반 변수.
	String x = "x";

	//// static 변수 ////
	// final 키워드를 붙이면 변수가 상수가 됨. 처음에 값이 들어가면 이후 값을 못 바꿈.
	// 상수 명명 국룰 = 이름을 다 대문자로 씀.

	final static String DOT = "🎁";

	public static void line() {
//		So.w(x);	// 일반 멤버 변수는 못씀.
		for (int i = 0; i < 32; i = i + 1) {
			So.p(DOT); // static 멤버 변수는 사용 가능.
		}
		So.ln("");
	}

	public static void title() {
		line();
		So.ln("************** 고양이 카페   ***************");
		line();
	}
}