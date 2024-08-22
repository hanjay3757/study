package com.peisia.kiosk.catcafe;

import com.peisia.util.Cw;

//자동임포트 단축키: ctrl+shift+o(영문자O)
public class Kiosk {

	public static final String VERSION = "0.0.11"; // 버전 표시용.

	void run() {
		KioskObj.productLoad(); // 상품로드
		Disp.title();
		xx: while (true) {
			Cw.wn("명령 입력[1.음료선택/2.디저트선택/e.프로그램종료]:");
			KioskObj.cmd = KioskObj.sc.next();
			switch (KioskObj.cmd) {
			case "1":
				ProcMenuDrink.run();
				break;
			case "2":
				ProcMenuDessert.run();
				break;
			case "e":
				Cw.wn("장바구니에 담긴 상품 갯수:" + KioskObj.basket.size());
				int sum = 0;
				for (Order o : KioskObj.basket) { // 향상된for문(for-each)으로 적용
					sum = sum + o.selectedProduct.price;
				}
				Cw.wn("계산하실 금액은 :" + sum + "원 입니다.");

				Cw.wn("===================="); //// 산거 리스팅 ////
				for (Order o : KioskObj.basket) {
					Cw.wn(o.selectedProduct.name);
				}
				Cw.wn("====================");
				Cw.wn("프로그램종료");
				break xx;
			}
		}
	}
}