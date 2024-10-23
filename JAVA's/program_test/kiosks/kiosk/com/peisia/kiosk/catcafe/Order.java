package com.peisia.kiosk.catcafe;

import com.peisia.kiosk.catcafe.product.Product;

public class Order {
	
	public Product selectedProduct;
	public int optionHotCold = 0;	//1: hot, 2:cold

	public Order(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Order(Product selectedProduct, int optionHotCold) {
		this.selectedProduct = selectedProduct;
		this.optionHotCold = optionHotCold;
	}
}
