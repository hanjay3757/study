package com.games;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<GameObj> x = new ArrayList<>();
		GameObj elf = new Character();
		elf.name = "엘프";
		((Character) elf).hp = 1;

		// 할어버지로 갔다가 자기자신으로 다시 형변환 (변신)
		// 상속으로 가도 재정의 한 함수가 있으면 재정의한 함수를 작동
		x.add(elf);
		Item book = new Item();
		book.weight = 100;
		book.name = "마도서인줄 알았쥐?";
		x.add(book);
		Item t = new Item();
		t.weight = 50;
		t.name = "뭘봐";
		x.add(t);
// 여러 함수 생성 가능
		Sword shortSword = new Sword();
		shortSword.name = "단검";
		shortSword.attack = 100;
		shortSword.weight = 10;
		x.add(shortSword);
// ArrayList 에 정보가 담김 단일 개체는 안되는데 상속관계에서는 허용 ((형변환이 일어남.
		// 자기들이 만든 변수 함수 못씀

		for (GameObj y : x) {

			y.info();
// 100 마리 만들었을때 각각의 info(attack) 각각의 재정의로 적용
		}
	}
}
