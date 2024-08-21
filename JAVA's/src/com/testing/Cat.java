package com.testing;

public class Cat {
	String name;
	int age;
	long id;
	float inch = 3.5f;
	double pi = 3.141592d;
	boolean isMine = false;

	void info() {
		String s = "고양이 이름은 " + name + "이고 나이는 " + age + "살 임";
		System.out.println(s);
	}
}
