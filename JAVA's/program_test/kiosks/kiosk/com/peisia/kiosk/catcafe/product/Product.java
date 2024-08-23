package com.peisia.kiosk.catcafe.product;

public class Product {
	//1.변수들
	
	////	접근 지정자(제한자) 4종	p.213,280 ////
	//public, private, 
	//default(직접 작성하지 않으며 안쓰면<공란으로 비워두면> 이 default가 적용됨)
	//protected
	public String name;
	public int price;
	
	public Product(String xx, int yy){
		name = xx;
		price = yy;
	}
	
	//2.함수들 (메인 말고)
	void info() {
		System.out.println("상품명:"+name+" 가격:"+price);
	}
	
}
