package com.practice;

public class GamecObj {
	String name;
	int hp;

	void info() {
		System.out.println("이름:" + name + "\n" + "체력:" + hp);
		// 할아버지는 자식꺼 못 갖고옴 제한 생김
	}
}
