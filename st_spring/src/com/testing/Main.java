package com.testing;

public class Main {
	public static void main(String[] args) {
		Cat cat1 = new Cat();
		Cat cat2 = new Cat();

		cat1.name = "네오";
		cat1.age = 5;

		cat1.info();

		for (double i = 3.141592d; i >= 10; i++) {
			cat2.name = "키팅";
			cat2.age = 10;
			cat2.info();
		}
	}
}
