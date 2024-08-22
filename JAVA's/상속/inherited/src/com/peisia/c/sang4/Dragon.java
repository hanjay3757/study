package com.peisia.c.sang4;

import com.peisia.c.util.So;

public class Dragon extends Monster{

	@Override
	void attack(Player p) {
		attack = 20;
		So.ln(String.format("브레스로 %s에게 피해를 %d 줌",p.name,attack));
	}
	
}
