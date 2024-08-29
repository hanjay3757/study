package com.hashmap;

import java.util.HashMap;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		HashMap<String, Food> foods = new HashMap<>();

		foods.put("라면", new Food("라면", true, 3500));
		foods.put("냉면", new Food("냉면", false, 6500));
		foods.put("쫄면", new Food("쫄면", true, 4000));

		Scanner sc = new Scanner(System.in);
		System.out.println(" 원하시는 음식을 골라 주세요 ");
		String cmd = sc.next();
		Food selectFood = foods.get(cmd);
		System.out.println(" 선택한 음식은 : " + selectFood.name);
		System.out.println(" 음식의 온도는 : " + selectFood.isHot);
		System.out.println(" 가격은 : " + selectFood.price);
	}
}
