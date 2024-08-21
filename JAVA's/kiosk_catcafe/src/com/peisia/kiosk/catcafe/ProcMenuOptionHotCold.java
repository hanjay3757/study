package com.peisia.kiosk.catcafe;

import com.peisia.util.Cw;

public class ProcMenuOptionHotCold {
	
	public static void run() {
		loop:while(true) {
			Cw.wn("[1.hot/2.cold/x.이전메뉴로]");
			KioskObj.cmd = KioskObj.sc.next();
			switch(KioskObj.cmd) {
			case "1":
				Cw.wn("hot 선택됨. 이전 메뉴 이동");
				KioskObj.basket.add(new Order(KioskObj.products.get(0),1));	//오더 추가
				break loop;
			case "2":
				Cw.wn("ice 선택됨. 이전 메뉴 이동");
				KioskObj.basket.add(new Order(KioskObj.products.get(0),2));	//오더 추가
				break loop;
			case "x":
				Cw.wn("이전 메뉴 이동");
				break loop;
			}
		}
	}
}
