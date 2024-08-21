package com.rpg;

public class Class {

	public static void main(String[] args) {
		Rpg player = new Rpg("냥피스", 100, 20, "엘프");
		Rpg monster = new Rpg("오크", 200, 10, "오크");

		player.info();
		monster.info();

	}

}
