package com.peisia.kiosk.catcafe;

import com.peisia.util.Cw;

public class Disp {
	String x = "x";	//일반 멤버 변수.
	
	//final 키워드를 붙이면 변수가 상수가 됨. 처음에 값이 들어가면 이후 값을 못 바꿈.
	//상수 명명 국룰 = 이름을 다 대문자로 씀.
	//static 변수:Disp.DOT 식으로 객체 생성없이 아무데서 사용 가능.
	//	> 같은 식구 ex. line() 들은 그냥 DOT 이라고 쓸 수 있음.
	final static String DOT = "★";	
	final static int DOT_COUNT = 48;	
	public static void line() {		//static 함수:Disp.line() 식으로 객체 생성없이 아무데서 사용 가능.
//		Cw.w(x);	// 일반 멤버 변수는 못씀.
		for(int i=0;i<DOT_COUNT;i=i+1) {
			Cw.w(DOT);	// static 멤버 변수는 사용 가능.
		}
		Cw.wn();
	}
	
	public static void title() {
		line();
		Cw.wn("************** 고양이 카페   ***************");
		line();
	}

}
