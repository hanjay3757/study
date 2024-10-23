package com.practice;

public class Item extends GameObj {
//	String name;

	int 수명;

	void info() {
		System.out.println("이름:" + name + "\n" + "무게:" + weight + "\n" + "수명: " + 수명);
	}
	// 할아버지 개체도 만들어짐
	// 메모리 공간안에 2개 생성 변수 공간
}
