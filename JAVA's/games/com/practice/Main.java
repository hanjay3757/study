package com.practice;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<GameObj> y = new ArrayList<>();
		// 원하는 상속 위치
		GameObj troll = new Character();
		troll.name = "트롤";
		((Character) troll).hp = 100;
		((Character) troll).attack = 10;
		y.add(troll);
		GameObj elf = new Character();
		elf.name = "너구리";
		((Character) elf).hp = 50;
		((Character) elf).attack = 30;
		y.add(elf);

		Item club = new Item();
		club.name = "사랑의빠따";
		club.weight = 500;
		club.수명 = 20;
		y.add(club);

//		Item belt = new Item();
//		belt.name = "1t";
//		belt.weight = 1;
//		y.add(belt);
		// ArrayList 생성

		for (GameObj a : y) {
			a.info();
		}

	}
}
