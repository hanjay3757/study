package com.peisia.kiosk.catcafe;

import com.peisia.util.Cw;

public class Disp {
	String x = "x";
	final static String DOT = "*";
	final static int DOT_COUNT = 48;

	public static void line() {
		for (int i = 0; i < DOT_COUNT; i++) {
//			((오른쪽 덩어리의 집합 낱개) :(x) )
			// string array, 배열 골고로 많을때 랜덤하게 나온다면
			Cw.w(DOT);
		}
		Cw.wn();
	}

	public static void title() {
		line();
		Cw.wn("************** 고양이 카페   ***************");
		line();
	}
}
