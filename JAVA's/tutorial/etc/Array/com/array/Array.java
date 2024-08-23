package com.array;

import java.util.ArrayList;

public class Array {

	public static void main(String[] args) {
		// 자동임포트 ctrl+shift+o(영문자O)
		ArrayList<String> xx = new ArrayList<String>();

		// 숫자 배열은 아래처럼 선언해서 쓰면 됨
		ArrayList<Integer> aa = new ArrayList<Integer>();
		aa.add(1);

		xx.add("고양이"); // 고양이가 들어감. index 0. 이제 1개.
		xx.add("개"); // 개가 들어감. index 1. 2개됨. 넣을때 마다 크기가 늘어남.
		xx.set(1, "강아지"); // 개 자리에 강아지가 들어감.
		xx.add("너굴맨"); // 너굴맨이 들어감. index 2. 3개됨.
		xx.remove(2); // index 2에 있는거 제거함. 자리도 없애는거라 이제 갯수 2개됨. 뺄때마다 크기가 줄어듬.
//		xx.clear();		//싹다 지움. 수량도 0됨.
		System.out.println("몇개?:" + xx.size());
		for (int i = 0; i < xx.size(); i = i + 1) {
			System.out.println(xx.get(i));
		}
		boolean hasCat = xx.contains("고양이");
		if (hasCat == true) {
			System.out.println("고양이 있음");
		} else {
			System.out.println("고양이 없음");
		}

	}

}
