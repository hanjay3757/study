package com.practice;

public class GameObj {
	String name;
	int weight;
	int hp;

	void info() {
		System.out.println("이름:" + name + "\n" + "무게:" + weight);
		// 할아버지는 자식꺼 못 갖고옴 제한 생김
	}
}
