package com.hashset;

import java.util.HashSet;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<>();
		int r = 0;
		while (true) {
			r = (int) (Math.random() * 45 + 1);
			hs.add(r);
			// 무한 루프 중복 안됨
			if (hs.size() == 6) {
				break;
				// 6개의 숫자 나오면 코드 종료
			}
		}
		Iterator<Integer> it = hs.iterator();

		System.out.println("-while,next()으로 꺼내기-");
		while (it.hasNext()) {
			int s = it.next();
			// 하나씩 숫자를 꺼냄
			System.out.println(s + " ");
		}
	}
}
