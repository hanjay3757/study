function Monster(name, hp, attack, exp, gold) {
	this.name = name;	// 이름
	this.hp = hp;	// 현재 체력. 생명력
	this.max_hp = hp;	// 최대 체력. 생명력
	this.attack = attack;	// 공격력
	this.exp = exp;	// 얘를 잡으면 얻는 경험치
	this.next_exp = exp;
	this.gold = gold;	// 얘 잡으면 주는 골드

	this.info = function () {
		document.write("[" + this.name + "(💖" + this.hp + " / " + this.max_hp + ")]" + "(exp: " + this.exp + "/" + this.next_exp + ")"
			+ "🍁:" + this.gold
		);
	}
}

function Character(name, hp, attack, exp, gold) {
	this.name = name;	// 이름
	this.hp = hp;	// 현재 체력. 생명력
	this.max_hp = hp;	// 최대 체력. 생명력
	this.attack = attack;	// 공격력
	this.exp = exp;	// 얘를 잡으면 얻는 경험치
	this.next_exp = exp;
	this.gold = gold;

	this.info = function () {
		document.write("[" + this.name + "(💖" + this.hp + " / " + this.max_hp + ")]" + "(exp: " + this.exp + "/" + this.next_exp + ")"
			+ "🍁:" + this.gold
		);
	}
}
