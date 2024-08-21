package com.lotto.auto;

public class Lotto {
	int getRandomNumber(int n) {
//		for (int i = 0; i < 6; i++) {
//			x[i] = (int) (Math.random() * 45 + 1);
//		}
		int r = (int) (Math.random() * n + 1);
		return r;
	}

//배열 참조
	void run() {
		int comLotto = getRandomNumber(6);
		System.out.println(comLotto);
	}
}
