package com.hashmap;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// todo
		// hashmap
		// 음식 리스트를 등록
		// 음식을 검색
		HashMap<String, Food> foods = new HashMap<>();
		Food f1 = new Food("라면", true, 3500);
		Food f2 = new Food("냉면", false, 12000);
		foods.put("라면", f1);
		foods.put("냉면", f2);
//		foods.put("RAMEN", new Food("냉면", false, 12000));
		Scanner sc = new Scanner(System.in);
		System.out.println("원하는 음식 이름을 입력");
		String cmd = sc.next();
		Food selectFood = foods.get(cmd);// "ICE_NOODLE"
		System.out.println("주무하신 음식은:" + selectFood.name);
		System.out.println("hot/cold 는:" + selectFood.isHot);
		System.out.println("가격:" + selectFood.price);

	}
}
