package com.util;

public class Cw {
	private static final String DOT = "🐈‍";
	private static final int LINE_LENGTH = 32;
	// 여러군데 쓸려고 상수취급/ 32 길이를 규격화

	public static void w(String s) {
		System.out.println(s);
	}

	public static void wn(String s) {
		System.out.println(s);
	}

	public static void wn() {
		System.out.println();
	}

	public static void line() {
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
