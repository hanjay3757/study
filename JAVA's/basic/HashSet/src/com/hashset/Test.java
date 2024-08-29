package com.hashset;

import java.util.HashSet;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<>();
		int r = 0;
		while (true) {
			r = (int) (Math.random() * 45 + 1);
			hs.add(r);
			if (hs.size() == 6) {
				break;
			}
		}
		Iterator<Integer> it = hs.iterator();
		System.out.println("while,next()");
		while (it.hasNext()) {
			int s = it.next();
			System.out.println(s + " ");
		}
	}
}
