package com.kiosk.v7;

import java.util.ArrayList;
import java.util.Scanner;

public class Kiosk {
	ProcMenuDrink procMenuDrink = new ProcMenuDrink();

	public static ArrayList<Product> basket = new ArrayList<Product>();

	// 자동임포트 단축키: ctrl+shift+o(영문자O)
	public static Scanner sc = new Scanner(System.in);

	public static Product p1 = new Product("아아", 1000);
	public static Product p2 = new Product("뜨아", 1500);
	public static Product p3 = new Product("오렌지쥬스", 2000);
	public static String cmd;

// static 매개변수로 줘서 고정 따른 클래스에서도 땡겨 쓸수 있겠끔. 
//	프로그램 실행되기도 전에 최초의 셋팅 new Product 먼저 생성 메인메소드 = 실행 되기 직전; 
	// 종료될때까지도 유지
	void run() {
		Display.title();
		xx: while (true) {
			System.out.print("명령 입력[1.음료선택/2.디저트선택/e.프로그램종료]:");
			cmd = sc.next();
			switch (cmd) {
			case "1":
				procMenuDrink.run();
				break;
			case "2":
				System.out.println("2번");
				break;
			case "e":
				System.out.println("프로그램종료");

				int count = basket.size();
				System.out.println("장바구니에 담긴 상품 갯수:" + count);

				int sum = 0;
				for (int i = 0; i < basket.size(); i = i + 1) {
					sum = sum + basket.get(i).price;
				}
				System.out.println("계산하실 금액은 :" + sum + "원 입니다.");

				break xx;
			}
		}
	}

}