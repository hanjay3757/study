package com.lotto.v3;

public class Lotto {
	void run() {
		System.out.println("==== 로또 프로그램 ====");

// alt +shift + r 이름 한번에 바꾸기
		int p[] = { 1, 2, 3, 4, 5, 6 };
		int r[] = new int[6];
		while (true) {
			r[0] = (int) (Math.random() * 45 + 1);
			break;
		}

		while (true) {
			r[1] = (int) (Math.random() * 45 + 1);
			if (r[1] != r[0]) {
				break;
			}
		}
		while (true) {
			r[2] = (int) (Math.random() * 45 + 1);
			if (r[2] != r[1] && r[2] != r[0]) {
				break;
			}
		}
		while (true) {
			r[3] = (int) (Math.random() * 45 + 1);
			if (r[3] != r[2] && r[3] != r[1] && r[3] != r[0]) {
				break;
			}
		}
		while (true) {
			r[4] = (int) (Math.random() * 45 + 1);
			if (r[4] != r[3] && r[4] != r[2] && r[4] != r[1] && r[4] != r[0]) {
				break;
			}
		}
		while (true) {
			r[5] = (int) (Math.random() * 45 + 1);
			if (r[5] != r[4] && r[5] != r[3] && r[5] != r[2] && r[5] != r[1] && r[5] != r[0]) {
				break;
			}
		}

		System.out.print("유저번호:");
		for (int i = 0; i < p.length; i++) {
			System.out.print(p[i] + " ");
		}
		System.out.println();
		System.out.print("추첨번호:");
		// println 하면 한칸 띄어짐
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
