package com.practice;

public class Character extends GameObj {
//	String name; extend 상속 받는다는 뜻 GameObj 에서 
	int hp;
	int attack;

	void info() {
		System.out.println("이름:" + name + "\n" + "hp: " + hp + "\t" + "공격력: " + attack);
		// 함수 재정의 상속받는거 무시하고 내맘되로 정의할래 *형변환
		// 별개 함수 만들면 상관 x info 함수를 한꺼번에
	}
}
