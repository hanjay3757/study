package com.lotto.re;

public class Lotto {
	void getRandomNumber(int x[]) {
		for (int i = 0; i < 6; i++) {
			x[i] = (int) (Math.random() * 45 + 1);
			if (x[i] != x[i]) {
				break;
			}
		}
	}
//배열 참조

	void run() {

		int p[] = new int[6];
		int r[] = new int[6];
		System.out.println("로또당");
		getRandomNumber(p);
		getRandomNumber(r);

		System.out.print("유저번호:");
		for (

				int i = 0; i < p.length; i++) {
			System.out.print(p[i] + " ");
		}
		System.out.println();
		System.out.print("추첨번호:");
		for (int i = 0; i < p.length; i++) {

			System.out.print(r[i] + " ");

		}
		int win = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (p[i] == r[j]) {

					win = win + 1;
					break;
				}
			}

		}
		System.out.println("맞춘갯수: " + win);

	}
}
