package com.peisia.cat;

public class ProcMenuDrink {
	static public void run() {
		yy: while (true) {
			Kiosk.p1.info();
			Kiosk.p2.info();
			Kiosk.p3.info();

			System.out.println("[1.아아/2.뜨아/3.오렌지쥬스/x.이전메뉴로]");
			System.out.println("");
			Kiosk.cmd = Kiosk.sc.next();
			switch (Kiosk.cmd) {
			case "1":
				System.out.println("아아 선택됨");
				Kiosk.basket.add(Kiosk.p1);

				break;

			case "2":
				System.out.println("뜨아 선택됨");
				Kiosk.basket.add(Kiosk.p2);
				break;
			case "3":
				System.out.println("오렌지쥬스 선택됨");
				Kiosk.basket.add(Kiosk.p3);
				break;
			case "x":
				System.out.println("이전 메뉴 이동");
				break yy;
			}

		}
	}
}