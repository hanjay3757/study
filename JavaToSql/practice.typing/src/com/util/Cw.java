package com.util;

public class Cw {
	private static final String DOT = "👀";
	private static final int LINE_LENGTH = 32;

	public static void w(String s) {
		System.out.print(s);
//println 하면 한칸 밑으로 반복
	}

	public static void wn(String s) {
		System.out.print(s);
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
