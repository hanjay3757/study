package com.rpg;

public class Rpg {
	String name;
	int hp;
	int attack;
	String race;

	Rpg(String name, int hp, int attack, String race) {

		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.race = race;
	}

	void info() {
		System.out.println(this.race + "가 태어남");
		System.out.println("이름:" + this.name + "[" + this.hp + "]" + " 공격력:" + "[" + this.attack + "]");
	}

}