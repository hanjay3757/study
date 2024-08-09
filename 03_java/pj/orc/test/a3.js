function Monster(name, hp, attack) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
    this.info = function () {
        document.write("이름:" + this.name + " hp:" + this.hp + " 공격력" + this.attack);
    }
}

function Character(name, hp, attack) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
    this.info = function () {
        document.write(" 이름:" + this.name + " hp:" + this.hp + " 공격력" + this.attack);
    }
}

var cat_v1 = new Monster("미야옹", 100, 10);
var cat_v2 = new Character("야옹이", 100, 10);


cat_v1.info();
cat_v2.info();

hr();
dw("전투");
hr();


function proc_battle() {
    var cat_v1_attack = r(cat_v1.attack);
    var cat_v2_attack = r(cat_v2.attack);

    hr();
    dw("🐱‍👤미야옹의 데미지🐾:" + cat_v1_attack);
    hr();
    dw("≽^•⩊•^≼야옹이의 데미지🐱‍🐉:" + cat_v2_attack);
    hr();

    cat_v1.hp = cat_v1.hp - cat_v2_attack;
    cat_v2.hp = cat_v2.hp - cat_v1_attack;

    if (cat_v1.hp < 0) {
        cat_v1.hp = 0;
    }
    if (cat_v2.hp < 0) {
        cat_v2.hp = 0;
    }
    cat_v1.info();
    cat_v2.info();
}
while (true) {
    proc_battle();
    if (cat_v1.hp <= 0 || cat_v2.hp <= 0) {
        break;
    }
}
