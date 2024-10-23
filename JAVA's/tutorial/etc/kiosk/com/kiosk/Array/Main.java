package com.kiosk.Array;

import java.util.Scanner;

import com.kiosk.v3.kiosk;

public class Main {

	public static void main(String[] args) {
		System.out.println("============================================");
		System.out.println("================= 고양이카페       ===========");
		System.out.println("============================================");
		// ctrl + shift + o(영문자). 자동 임포트.
		kiosk p1 = new Kiosk("아아", 1000);
		kiosk p2 = new Kiosk("뜨아", 2000);
		kiosk p3 = new Kiosk(5000, "마카롱");

		Scanner sc = new Scanner(System.in);
		String cmd;
		loop_a: while (true) {
			System.out.print("명령:[1.음료/2.디저트/e.종료]");
			cmd = sc.next();
			switch (cmd) {
			case "1":
				System.out.println("==================================");
				System.out.println("============= 음료 리스트    =======");
				System.out.println("==================================");
				p1.info();
				p2.info();
				loop_b: while (true) {
					System.out.print("명령:[1.아아/2.뜨아/x.이전메뉴]");
					cmd = sc.next();
					switch (cmd) {
					case "1":
						System.out.println("아아가 1개 선택됐습니다.");
						break;
					case "2":
						System.out.println("뜨아가 1개 선택됐습니다.");
						break;
					case "x":
						break loop_b;
					}
				}

				break;
			case "2":
				System.out.println("==================================");
				System.out.println("============= 디저트 리스트    =======");
				System.out.println("==================================");
				p3.info();
				break;
			case "e":
				break loop_a;
			}
		}
		System.out.println("프로그램 종료");
	}

}
