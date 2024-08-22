package delete;

import java.util.ArrayList;
import java.util.Scanner;

public class KioskObj {
	public static ArrayList<Order> basket = new ArrayList<>();
	public static ArrayList<Product> products = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	String cmd;

	void productLoad() {
		products.add(new Drink("커피", 1000));
		products.add(new Drink("오렌지쥬스", 2000));
		products.add(new Dessert("마카롱", 10.000));

	}

}
