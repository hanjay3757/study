package com.lotto_01;

public class Lotto {

	public void run() {
//		int x[] = {1,2};
		// 로또 로직
		int p[] = { 1, 2, 3, 4, 5, 6 };
		int r[] = new int[6];
		r[0] = (int) (Math.random() * 45 + 1);
		r[1] = (int) (Math.random() * 45 + 1);
		r[2] = (int) (Math.random() * 45 + 1);
		r[3] = (int) (Math.random() * 45 + 1);
		r[4] = (int) (Math.random() * 45 + 1);
		r[5] = (int) (Math.random() * 45 + 1);
		System.out.println("=== 당첨 번호 ===");
		System.out.print(r[0] + " ");
		System.out.print(r[1] + " ");
		System.out.print(r[2] + " ");
		System.out.print(r[3] + " ");
		System.out.print(r[4] + " ");
		System.out.print(r[5] + " ");

		int win = 0;
		if (p[0] == r[0]) {
			win++;
		}
		if (p[0] == r[1]) {
			win++;
		}
		if (p[0] == r[2]) {
			win++;
		}
		if (p[0] == r[3]) {
			win++;
		}
		if (p[0] == r[4]) {
			win++;
		}
		if (p[0] == r[5]) {
			win++;
		}
		if (p[1] == r[0]) {
			win++;
		}
		if (p[1] == r[1]) {
			win++;
		}
		if (p[1] == r[2]) {
			win++;
		}
		if (p[1] == r[3]) {
			win++;
		}
		if (p[1] == r[4]) {
			win++;
		}
		if (p[1] == r[5]) {
			win++;
		}
		if (p[2] == r[0]) {
			win++;
		}
		if (p[2] == r[1]) {
			win++;
		}
		if (p[2] == r[2]) {
			win++;
		}
		if (p[2] == r[3]) {
			win++;
		}
		if (p[2] == r[4]) {
			win++;
		}
		if (p[2] == r[5]) {
			win++;
		}
		if (p[3] == r[0]) {
			win++;
		}
		if (p[3] == r[1]) {
			win++;
		}
		if (p[3] == r[2]) {
			win++;
		}
		if (p[3] == r[3]) {
			win++;
		}
		if (p[3] == r[4]) {
			win++;
		}
		if (p[3] == r[5]) {
			win++;
		}
		if (p[4] == r[0]) {
			win++;
		}
		if (p[4] == r[1]) {
			win++;
		}
		if (p[4] == r[2]) {
			win++;
		}
		if (p[4] == r[3]) {
			win++;
		}
		if (p[4] == r[4]) {
			win++;
		}
		if (p[4] == r[5]) {
			win++;
		}
		if (p[5] == r[0]) {
			win++;
		}
		if (p[5] == r[1]) {
			win++;
		}
		if (p[5] == r[2]) {
			win++;
		}
		if (p[5] == r[3]) {
			win++;
		}
		if (p[5] == r[4]) {
			win++;
		}
		if (p[5] == r[5]) {
			win++;
		}

		System.out.println("맞춘갯수:" + win);

	}

}
