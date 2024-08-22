package com.peisia.c.sang4;

import com.peisia.c.util.So;

public class Elf extends Monster{

	@Override
	void attack(Player p) {
		attack = 7;
		So.ln(String.format("활로 %s에게 피해를 %d 줌",p.name,attack));
		plusPoisonDamage(p);
	}
	
	void plusPoisonDamage(Player p) {
		So.ln(String.format("%s에게 독데미지 %d 를 추가로 줌",p.name,attack));
	}
	
}
