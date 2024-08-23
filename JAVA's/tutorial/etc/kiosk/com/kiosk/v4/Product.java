package com.kiosk.v4;

public class Product {
	// 1.변수들
	String name;
	int price;

	Product(String xx, int yy) {
		name = xx;
		price = yy;
	}
//	Product(String xy, int xa) {
//		name = xx;
//		price = yy;
//	}
	// 갯수 와 타입이 같아서 에러 바꿀꺼면 int string으로 바꾸면 가능;

// 생성자 선언  두개이상일 경우 오버로딩 
	// 똑같은 거

	// 2.함수들 (메인 말고)
	void info() {
		System.out.println("상품명:" + name + " 가격:" + price);
	}

}
