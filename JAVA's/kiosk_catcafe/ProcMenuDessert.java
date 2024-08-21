package com.peisia.kiosk.catcafe1;

import com.peisia.util.Cw;

public class ProcMenuDessert {
	
	public static void run() {
		
		for(Product p:KioskObj.products) {	//메뉴출력
			Cw.wn(p.name);
		}
		yy:while(true) {
			Cw.wn("[1.마카롱/x.이전메뉴로]");
			KioskObj.cmd = KioskObj.sc.next();
			switch(KioskObj.cmd) {
			case "1":
				Cw.wn(KioskObj.products.get(2).name+" 선택됨");
				KioskObj.basket.add(new Order(KioskObj.products.get(2)));	//오더 추가
				break;
			case "x":
				Cw.wn("이전 메뉴 이동");
				break yy;
			}
		}
	}
}
