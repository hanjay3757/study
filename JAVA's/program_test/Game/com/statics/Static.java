package com.statics;

public class Static {
	int n; // 멤버 변수를 추가 못하거나 하진 않음.

	/*
	 * 주사위 굴리는 함수
	 * 
	 * static 키워드 위치는 static public 또는 public static 둘다 됨.
	 * 
	 * static 함수를 만들면 해당 클래스.. 여기서는 Dice 클래스를 new 해서 객체 생성하지 않아도 Dice.roll(100) <<
	 * 이런식으로 함수 실행이 가능해짐. 매개변수는 못끌고 오지만 vsc에서 사설변수 function 처럼 변수를 생성후 new 어쩌구를 생성
	 * 안해도 작동 하게끔 해줌
	 */
	static public int roll(int n) {
		int r = (int) (Math.random() * n + 1);
		return r;
	}

	// 일반 함수를 멤버 함수로 못 만드는건 아님.
	// 둘다 섞여 있어도 됨.
	// 단, 일반 함수는 객체를 생성해야만 사용 가능함.
	void x() {
	}
}
