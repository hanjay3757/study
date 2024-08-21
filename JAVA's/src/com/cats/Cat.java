package com.cats;

public class Cat {
	int age;
	String name;
	String hobby;
//
//	void x() {
////아무것도 없으면 void
//	}
//
//	int y() {
//		return 100;
//	}

	/*
	 * int add(int a, int b) { // 매개 변수 int sum = a + b; return sum; }
	 * 
	 * int add(int a, int b, int c) { // 매개 변수 int sum = a + b + c; // 함수 오버로딩
	 * return sum; } 적용 됨
	 */
//	public Cat() {
//		System.out.println("고양이 태어남!!!");
//	}
	void info() {
		System.out.println("고양이 이름은 " + name + " 입니다" + "나이는 " + age + "살 입니다" + hobby + "가 취미입니다.");
	}

	public Cat(int age, String name, String hobby) {
//		생산자함수
		this.age = age;
		this.name = name;
		this.hobby = hobby;
	}
}
//	public Cat(int age, String name) {
//		this.age = age;
//		this.name = name;
//	}
//}