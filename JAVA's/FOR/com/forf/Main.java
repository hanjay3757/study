package com.forf;

import java.util.ArrayList;

public class Main {
	String name;
	int age;

	public static void main(String[] args) {
		ArrayList<String> animals = new ArrayList<>();
		animals.add("고양이");
		animals.add("개");
		animals.add("토끼");
//		
//		for(int i=0;i<animals.size();i=i+1) {
//			System.out.println(animals.get(i));
//		}
//		
		// 향상된 for문(for-each라고도 함)
		for (String x : animals) {
			System.out.println(x);
		}

//		 int a[] = { 1, 2, 3 };  for (int n : a) {  System.out.println(n); 
//		 배열 가능하다
	}
}
