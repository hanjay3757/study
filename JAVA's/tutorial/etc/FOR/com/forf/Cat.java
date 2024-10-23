package com.forf;

public class Cat {
	String name;
	int age;

	// 생성자 함수 자동으로 생성 하는 법:
	// 1.여기 클래스 아무데나 마우스 우클릭하고
	// 2.팝업메뉴에서-source-generate constructor using fields
	// 3.매개변수로 받을 멤버 변수 체크 후 generate 버튼 클릭.끝.
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
