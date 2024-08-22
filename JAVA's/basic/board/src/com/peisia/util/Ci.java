package com.peisia.util;

import java.util.Scanner;

/*
 * 입력을 담당하는 클래스. (Scanner 등)
 */
public class Ci {
	static Scanner sc = new Scanner(System.in);
	static public String r() {
		return sc.next();
	}
	static public String r(String comment) {
		Cw.w(comment+":");
		return sc.next();
	}
}
