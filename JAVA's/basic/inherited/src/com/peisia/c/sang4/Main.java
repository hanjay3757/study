package com.peisia.c.sang4;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
//		Son son = new Son();
//		Son2 son2 = new Son2();
//		Son3 son3 = new Son3();
////		son.kimchi();
//
//		ArrayList<Father> sons = new ArrayList<>();
//		
//		sons.add(son);
//		sons.add(son2);
//		sons.add(son3);
//		
//		for(Father f : sons) {
//			f.kimchi();
//		}
		

		Player p = new Player("엠피스",1000);
		
		ArrayList<Monster> ms = new ArrayList<>();
		ms.add(new Dragon());
		ms.add(new Dragon());
		ms.add(new Dragon());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Orc());
		ms.add(new Elf());
		ms.add(new Elf());
		ms.add(new Elf());
		ms.add(new Elf());
		ms.add(new Elf());
		
		for(Monster m : ms) {
			m.attack(p);
		}
	}
}