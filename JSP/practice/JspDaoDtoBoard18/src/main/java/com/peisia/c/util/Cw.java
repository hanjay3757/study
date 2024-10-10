package com.peisia.c.util;

public class Cw {
	private static final String DOT = "🐈‍";
	private static final int LINE_LENGTH = 32;

	public static void w(String s) {
		System.out.print(s);
	}

	public static void wn(String s) {
		System.out.println(s);
	}

	public static void wn() { /*
								 * // * 그냥 엔터하나 넣어주는 함수. wn 함수 - // * 오버로딩 위에꺼랑 같은건데 String s가 없는거 같은 값이 있는걸 //
								 * * 오버로딩 //
								 */
		System.out.println();
	}

	public static void line() { /* 선 긋는 함수 */
		for (int i = 0; i < LINE_LENGTH; i++) {
			w(DOT);
		}
		wn();
	}

	public static void dot() {
		w(DOT);
	}

	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" ");
		}
	}
}