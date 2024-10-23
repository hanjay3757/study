package com.kiosk.v8;

import java.util.ArrayList;
import java.util.Scanner;

public class Kiosk {
	ProcMenuDrink procMenuDrink = new ProcMenuDrink();
	public static ArrayList<Product> basket = new ArrayList<Product>();
	public static Scanner sc = new Scanner(System.in);
	public static Product p1 = new Product("아아", 1000);
	public static Product p2 = new Product("뜨아", 1500);
	public static Product p3 = new Product("오렌지쥬스", 2500);
	public static String cmd;

	void run() {
		Display.title();
		xx: while (true) {
			System.out.print("명령 입력[1.음료선택/2.디저트선택/e.계산]:");
			cmd = sc.next();
			switch (cmd) {
			case "1":
				procMenuDrink.run();
				break;
			case "2":
				System.out.println("2번");
				break;
			case "e":
				System.out.println("계산");

				int count = basket.size();
				System.out.println("장바구니에 담긴 상품 갯수:" + count);

				int sum = 0;
				for (Product x : basket) {
					sum = sum + x.price;
				}
				System.out.println("계산하실 금액은 :" + sum + "원 입니다.");
				break xx;

			}
		}
	}

}